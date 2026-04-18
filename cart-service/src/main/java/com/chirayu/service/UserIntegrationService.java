package com.chirayu.service;

import com.chirayu.dto.CartItemRequestDto;
import com.chirayu.exception.UserNotFoundException;
import com.chirayu.feignclients.ProductFeignClient;
import com.chirayu.feignclients.UserFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserIntegrationService {
    private final UserFeignClient userFeignClient;
    private final ProductFeignClient productFeignClient;

    @CircuitBreaker(name = "user-service-cb",fallbackMethod = "userFallBack")
    public Boolean fetchUserById(CartItemRequestDto requestDto){
        return userFeignClient.existById(requestDto.getUserId());
    }

    public Boolean userFallBack(CartItemRequestDto requestDto,Throwable throwable){
        log.error("User fall-back called");
        throw new UserNotFoundException("User does not exists in Database with given ID");
    }
}
