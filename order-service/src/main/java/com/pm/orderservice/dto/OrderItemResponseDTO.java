package com.pm.orderservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderItemResponseDTO {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}
