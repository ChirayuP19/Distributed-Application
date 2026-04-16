package com.pm.shippingservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShippingRequestDTO {
    private Long orderId;
    private String shippingMethod;
    private DeliveryType deliveryType;
    private String carrier;
}
