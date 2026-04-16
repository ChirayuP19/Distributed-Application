package com.pm.shippingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipping_info")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private String shippingMethod;
    private LocalDateTime shippingAt;
    private LocalDateTime deliveryAt;
    private String status;
    private String carrier;
}
