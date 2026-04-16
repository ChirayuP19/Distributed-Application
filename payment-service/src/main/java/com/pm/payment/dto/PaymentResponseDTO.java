package com.pm.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PaymentResponseDTO {
    private Long paymentId;
    private Long orderId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime paymentDate;
}
