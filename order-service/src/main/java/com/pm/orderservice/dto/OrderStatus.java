package com.pm.orderservice.dto;

public enum OrderStatus {
    PLACED,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    COMPLETED,
    PENDING,
    SHIPPING_FAILED,
    SHIPPING_CANCELED,
    WAITING_FOR_DELIVERY,
    IN_PROGRESS,
}
