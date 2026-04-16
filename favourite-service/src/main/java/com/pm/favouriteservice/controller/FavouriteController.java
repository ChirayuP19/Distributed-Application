package com.pm.favouriteservice.controller;

import com.pm.favouriteservice.dto.FavouriteRequestDto;
import com.pm.favouriteservice.dto.FavouriteResponseDto;
import com.pm.favouriteservice.service.FavouriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favourites")
@RequiredArgsConstructor
public class FavouriteController {
    private final FavouriteService favouriteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FavouriteResponseDto addFavourite(@RequestBody FavouriteRequestDto requestDto) {
        return favouriteService.addProductToFavourite(requestDto);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FavouriteResponseDto> getFavouriteByUserId(@PathVariable("userId") String userId) {
        return favouriteService.getFavouritesByUserId(userId);
    }

    @DeleteMapping("/users/{userId}/product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProductById(@PathVariable("userId") String userId, @PathVariable("productId") Long productId) {
        return favouriteService.removeFavouriteByProductId(userId, productId);
    }

}
