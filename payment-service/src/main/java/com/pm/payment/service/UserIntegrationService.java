package com.pm.payment.service;

import com.pm.payment.dto.PaymentRequestDTO;
import com.pm.payment.dto.UserDto;
import com.pm.payment.feignclients.UserFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserIntegrationService {

    private final UserFeignClient userFeignClient;

    @CircuitBreaker(name = "user-service-cb", fallbackMethod = "userFallBack")
    @RateLimiter(name = "userServiceLimiter",fallbackMethod = "userRateLimiterFallBack")
    public UserDto fetchUser(PaymentRequestDTO requestDTO) {
        return userFeignClient.findById(requestDTO.getUserId());
    }

    public UserDto userFallBack(PaymentRequestDTO requestDTO, Throwable throwable) {
        log.warn("User service down, using fallback. Reason: {}", throwable.getMessage());
        throw new IllegalArgumentException("User service down, using fallback. Please try after some time");
    }

    public UserDto userRateLimiterFallBack(PaymentRequestDTO requestDTO, Throwable throwable) {
        log.warn("User service down, using rateLimiterFallBack. Reason: {}", throwable.getMessage());
        throw new IllegalArgumentException("Too Many Requests ! Please try after some time");
    }
}
