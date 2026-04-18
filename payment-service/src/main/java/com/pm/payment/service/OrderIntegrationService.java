package com.pm.payment.service;

import com.pm.payment.dto.OrderResponseDTO;
import com.pm.payment.dto.PaymentRequestDTO;
import com.pm.payment.feignclients.OrderClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderIntegrationService {
    private final OrderClient orderClient;

    @CircuitBreaker(name = "order-service-cb", fallbackMethod = "orderFallbackMethod")
    public OrderResponseDTO fetchOrder(PaymentRequestDTO paymentRequestDTO) {
        return orderClient.getOrderById(paymentRequestDTO.getOrderId());
    }

    public OrderResponseDTO orderFallbackMethod(PaymentRequestDTO paymentRequestDTO, Throwable throwable) {
        log.error("OrderIntegrationService error", throwable);
        throw new IllegalArgumentException("Order service is down, please try again later ");
    }
}
