package com.pm.orderservice.mapper;

import com.pm.orderservice.dto.OrderItemResponseDTO;
import com.pm.orderservice.model.OrderItem;

public class OrderItemMapper {
    public static OrderItemResponseDTO toDto(OrderItem item) {
        return OrderItemResponseDTO.builder()
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .build();
    }
}
