package com.pm.shippingservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ShippingResponseDTO {
    private Long orderId;
    private String status;
    private String carrier;
    private LocalDateTime shippingAt;
    private LocalDateTime deliveryAt;
}
