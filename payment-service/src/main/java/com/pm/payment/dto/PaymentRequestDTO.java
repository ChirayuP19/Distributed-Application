package com.pm.payment.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequestDTO {
    private Long orderId;
    private String userId;
    private BigDecimal amount;
}
