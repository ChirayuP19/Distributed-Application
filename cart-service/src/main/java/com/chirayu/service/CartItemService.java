package com.chirayu.service;

import com.chirayu.dto.CartItemRequestDto;
import com.chirayu.dto.CartItemResponseDto;

import java.util.List;

public interface CartItemService {
    CartItemResponseDto addToCart(CartItemRequestDto requestDto);
    List<CartItemResponseDto> getUserCart (String userId);
    void removeItem(String userId,Long productId);
    CartItemResponseDto updateQuantity(CartItemRequestDto requestDto);
    void clearCart(String userId);
}
