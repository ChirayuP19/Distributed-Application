package com.chirayu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateDto {
    @NotNull(message = "please enter product ID")
    @Positive
    private Long productId;
    @NotNull(message = "stock cant be null")
    @Positive
    private Integer stock;
}
