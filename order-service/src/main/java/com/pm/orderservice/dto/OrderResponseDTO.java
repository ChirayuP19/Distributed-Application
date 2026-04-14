package com.pm.orderservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponseDTO {
    private Long orderId;
    private String userId;
    private BigDecimal totalPrice;
    private String status;
    private List<OrderItemResponseDTO> orderItems;
}
