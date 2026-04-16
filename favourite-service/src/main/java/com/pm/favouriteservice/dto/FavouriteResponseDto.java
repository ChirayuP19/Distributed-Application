package com.pm.favouriteservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class FavouriteResponseDto {
    private Long id;
    private String userId;
    private Long productId;
    private String productName;
    private BigDecimal price;
}
