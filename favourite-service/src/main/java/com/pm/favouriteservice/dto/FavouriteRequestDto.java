package com.pm.favouriteservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FavouriteRequestDto {
    private String userId;
    private Long productId;
}
