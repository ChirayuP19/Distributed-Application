package com.pm.orderservice.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CartItemResponseDto {
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "product_id")
    private Long productId;
    private Integer quantity;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private BigDecimal price;
    private BigDecimal totalPrice;
}
