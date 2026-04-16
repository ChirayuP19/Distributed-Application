package com.pm.favouriteservice.service;

import com.pm.favouriteservice.dto.FavouriteRequestDto;
import com.pm.favouriteservice.dto.FavouriteResponseDto;
import com.pm.favouriteservice.dto.ProductResponseDto;
import com.pm.favouriteservice.dto.UserDto;
import com.pm.favouriteservice.entity.Favourite;
import com.pm.favouriteservice.feignClients.ProductFeignClient;
import com.pm.favouriteservice.feignClients.UserFeignClient;
import com.pm.favouriteservice.repository.FavouriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavouriteServiceServiceImpl implements FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final UserFeignClient userFeignClient;
    private final ProductFeignClient productFeignClient;

    @Override
    public FavouriteResponseDto addProductToFavourite(FavouriteRequestDto requestDto) {
        UserDto user = userFeignClient.findById(requestDto.getUserId());
        ProductResponseDto product = productFeignClient.findById(requestDto.getProductId());
        if (user == null || product == null) {
            throw new IllegalArgumentException("User or Product not found");
        }

        favouriteRepository.findByUserIdAndProductId(requestDto.getUserId(), requestDto.getProductId())
                .ifPresent(favourite -> {
                    throw new IllegalArgumentException("Favourite already exists");
                });
        Favourite saved = favouriteRepository.save(toFavourite(requestDto));

        return FavouriteResponseDto.builder()
                .id(saved.getId())
                .userId(requestDto.getUserId())
                .productId(requestDto.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .build();
    }

    @Override
    public List<FavouriteResponseDto> getFavouritesByUserId(String userId) {
        List<Favourite> favourites = favouriteRepository.findByUserId(userId);
        if (favourites.isEmpty()) {
            throw new RuntimeException("No favourites found for user: " + userId);
        }

        List<Long> productsIds = favourites.stream()
                .map(Favourite::getProductId)
                .distinct()
                .toList();

        List<ProductResponseDto> products =
                productFeignClient.findAllByIds(productsIds);

        Map<Long, ProductResponseDto> productMap = products
                .stream()
                .collect(Collectors.toMap
                        (ProductResponseDto::getId, p -> p));

        return favourites.stream()
                .map(fav -> {
                    ProductResponseDto product = productMap.get(fav.getProductId());
                    return FavouriteResponseDto.builder()
                            .id(fav.getId())
                            .userId(fav.getUserId())
                            .productId(fav.getProductId())
                            .productName(product != null ? product.getProductName() : "N/A")
                            .price(product != null ? product.getPrice() : BigDecimal.ZERO)
                            .build();
                })
                .toList();
    }

    @Override
    public String removeFavouriteByProductId(String userId, Long productId) {
        UserDto userDto = userFeignClient.findById(userId);
        ProductResponseDto productDto = productFeignClient.findById(productId);
        if(userDto == null  || productDto == null){
            throw new IllegalArgumentException("User or Product not found");
        }
        Favourite favourite = favouriteRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new IllegalArgumentException
                        ("No Favourites product found with productId: " + productId));
        favouriteRepository.delete(favourite);
        return "Favourite Product ID:: "+productId+" removed successfully";
    }

    private Favourite toFavourite(FavouriteRequestDto requestDto) {
        return Favourite.builder()
                .userId(requestDto.getUserId())
                .productId(requestDto.getProductId())
                .build();
    }

}
