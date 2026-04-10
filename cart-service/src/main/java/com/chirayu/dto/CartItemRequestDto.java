package com.chirayu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartItemRequestDto {
    @NotNull(message = "userId is required")
    private String userId;
    @Positive
    private Long productId;
    @NotNull(message ="quantity can't be null")
    private Integer quantity;
}
