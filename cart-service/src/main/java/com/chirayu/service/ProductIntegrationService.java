package com.chirayu.service;

import com.chirayu.dto.CartItemRequestDto;
import com.chirayu.exception.ProductNotFoundException;
import com.chirayu.feignclients.ProductFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductIntegrationService {

    private final ProductFeignClient productFeignClient;

    @CircuitBreaker(name = "product-service-cb",fallbackMethod = "productFallBack")
    public Boolean productFetch(CartItemRequestDto requestDto) {
       return productFeignClient.isExistById(requestDto.getProductId());
    }

    public Boolean productFallBack(CartItemRequestDto requestDto,Throwable throwable) {
        log.error("Product fall-back called");
        throw new ProductNotFoundException("Product does not exists in Database with given ID");
    }
}
