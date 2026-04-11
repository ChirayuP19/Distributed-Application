package com.chirayu.service;

import com.chirayu.dto.CartItemRequestDto;
import com.chirayu.dto.CartItemResponseDto;
import com.chirayu.entity.CartItem;
import com.chirayu.feignclients.ProductFeignClient;
import com.chirayu.feignclients.UserFeignClient;
import com.chirayu.repositoty.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserFeignClient userFeignClient;
    private final ProductFeignClient productFeignClient;

    @Override
    public CartItemResponseDto addToCart(CartItemRequestDto requestDto) {

        Boolean userExistById = userFeignClient.existById(requestDto.getUserId());
        if (Boolean.FALSE.equals(userExistById))
            throw new IllegalArgumentException("User does not exists in Database with given ID:: "+requestDto.getUserId());
        Boolean existById = productFeignClient.isExistById(requestDto.getProductId());
        if(Boolean.FALSE.equals(existById))
            throw new IllegalArgumentException("Product does not exists in Database");
        Optional<CartItem> existingItem = cartItemRepository.findByUserIdAndProductId(requestDto.getUserId(), requestDto.getProductId());
        CartItem savedItem;
        CartItem cartItem;
        if (existingItem.isPresent()) {
            cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() == null ? requestDto.getQuantity() : cartItem.getQuantity() + requestDto.getQuantity());
        } else {
            cartItem = new CartItem();
            BeanUtils.copyProperties(requestDto, cartItem);

        }
        savedItem = cartItemRepository.save(cartItem);
        return mapToResponseDto(savedItem);
    }

    @Override
    public List<CartItemResponseDto> getUserCart(String userId) {
        return cartItemRepository.findByUserId(userId)
                .stream().map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public void removeItem(String userId, Long productId) {
        Optional<CartItem> existsCartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
        if (existsCartItem.isEmpty())
            throw new IllegalArgumentException("user Cart have not product with Id::" + productId);
        cartItemRepository.deleteByUserIdAndProductId(userId, productId);
    }

    @Override
    public CartItemResponseDto updateQuantity(CartItemRequestDto requestDto) {
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(requestDto.getUserId(), requestDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Item is not in the Cart"));
        Integer newQuantity = requestDto.getQuantity();
        if (newQuantity == null || newQuantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive");
        cartItem.setQuantity(cartItem.getQuantity() + newQuantity);
        cartItem.setUpdatedAt(LocalDateTime.now());
        CartItem dbCart = cartItemRepository.save(cartItem);
        return mapToResponseDto(dbCart);
    }

    @Override
    public void clearCart(String userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        cartItemRepository.deleteAll(cartItems);

    }

    public CartItemResponseDto mapToResponseDto(CartItem dbCartItem) {
        BigDecimal price = productFeignClient.productPriceById(dbCartItem.getProductId());
        BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(dbCartItem.getQuantity()));
        return CartItemResponseDto.builder()
                .id(dbCartItem.getId())
                .userId(dbCartItem.getUserId())
                .productId(dbCartItem.getProductId())
                .quantity(dbCartItem.getQuantity())
                .createdAt(dbCartItem.getCreatedAt())
                .price(price)
                .totalPrice(totalPrice)
                .build();
    }
}
