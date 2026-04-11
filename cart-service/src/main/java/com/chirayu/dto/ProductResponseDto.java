package com.chirayu.dto;

import java.math.BigDecimal;

public class ProductResponseDto {
    private Long productId;
    private String productName;   // optional
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;
}
