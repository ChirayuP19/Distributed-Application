package com.pm.orderservice.service;

import com.pm.orderservice.OrderRepository;
import com.pm.orderservice.dto.*;
import com.pm.orderservice.exception.UserNotFoundException;
import com.pm.orderservice.feignClients.CartItemFeignClient;
import com.pm.orderservice.feignClients.ProductFeignClient;
import com.pm.orderservice.feignClients.UserFeignClient;
import com.pm.orderservice.mapper.OrderItemMapper;
import com.pm.orderservice.model.Order;
import com.pm.orderservice.model.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserFeignClient userFeignClient;
    private final ProductFeignClient productFeignClient;
    private final CartItemFeignClient cartItemFeignClient;


    @Override
    public OrderResponseDTO placeOrder(PlaceOrderRequestDTO requestDTO) {

        UserDto userDto = validateUser(requestDTO.getUserId());
        if (userDto == null) {
            throw new UserNotFoundException("User not found with ID:: " + requestDTO.getUserId());
        }
        List<CartItemResponseDto> fetchCartItems = fetchCartItems(requestDTO.getUserId());
        if (fetchCartItems == null || fetchCartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty or not found with ID:: " + requestDTO.getUserId());
        }
        BigDecimal totalPrice = calculateTotalPrice(fetchCartItems);
        List<OrderItem> orderItems = buildOrderItems(fetchCartItems);

        Order order = createOrder(requestDTO, totalPrice, orderItems);
        Order savedOrder = orderRepository.save(order);
        cartItemFeignClient.clearUserCart(requestDTO.getUserId());
        return mapToOrderResponse(savedOrder);
    }

    private static Order createOrder(PlaceOrderRequestDTO requestDTO, BigDecimal totalPrice, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setUserId(requestDTO.getUserId());
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PLACED.toString());
        for (OrderItem item : orderItems) {
            item.setOrder(order);
        }
        order.setItems(orderItems);
        return order;
    }

    private UserDto validateUser(String userId) {
        return userFeignClient.findById(userId);
    }

    private List<CartItemResponseDto> fetchCartItems(String userId) {
        return cartItemFeignClient.getCartByUserID(userId);
    }

    private BigDecimal calculateTotalPrice(List<CartItemResponseDto> cartItems) {
        if (cartItems == null || cartItems.isEmpty()) {
            return BigDecimal.ZERO;
        }
        List<Long> listProductIds = cartItems
                .stream()
                .map(CartItemResponseDto::getProductId)
                .distinct()
                .toList();

        Map<Long, ProductResponseDto> productMap = productFeignClient.findAllByIds(listProductIds)
                .stream()
                .collect(Collectors
                        .toMap(ProductResponseDto::getId, Function.identity()));

        return cartItems.stream()
                .map(item -> productMap.get(item.getProductId())
                        .getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<OrderItem> buildOrderItems(List<CartItemResponseDto> cartItems) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItemResponseDto cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(productFeignClient.findById(cartItem.getProductId()).getPrice());
            orderItems.add(orderItem);
        }
        return orderItems;
    }
    private OrderResponseDTO mapToOrderResponse(Order savedOrder) {

        List<OrderItemResponseDTO> itemDTOS = savedOrder.getItems().stream()
                .map(OrderItemMapper::toDto)
                .toList();

        return OrderResponseDTO.builder()
               .orderId(savedOrder.getId())
               .userId(savedOrder.getUserId())
               .status(savedOrder.getStatus())
               .totalPrice(savedOrder.getTotalPrice())
               .orderItems(itemDTOS)
               .build();
    }
}
