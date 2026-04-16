package com.pm.favouriteservice.service;

import com.pm.favouriteservice.dto.FavouriteRequestDto;
import com.pm.favouriteservice.dto.FavouriteResponseDto;

import java.util.List;

public interface FavouriteService {

    FavouriteResponseDto addProductToFavourite(FavouriteRequestDto requestDto);

    List<FavouriteResponseDto> getFavouritesByUserId(String userId);
    String removeFavouriteByProductId(String userId, Long productId);
}
