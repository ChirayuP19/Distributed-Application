package com.pm.favouriteservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    @JsonProperty("name")
    private String productName;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;
}
