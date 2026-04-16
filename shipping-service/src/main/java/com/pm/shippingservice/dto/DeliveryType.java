package com.pm.shippingservice.dto;

import java.time.LocalDateTime;

public enum DeliveryType {
    PREMIUM(2),
    FAST(3),
    NORMAL(5);

    private final int days;
    DeliveryType(int days) {
        this.days = days;
    }

    public LocalDateTime calculateEstimatedTime() {
        return LocalDateTime.now().plusDays(days);
    }
}
