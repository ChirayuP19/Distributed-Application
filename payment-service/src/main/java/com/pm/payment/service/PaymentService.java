package com.pm.payment.service;

import com.pm.payment.dto.PaymentRequestDTO;
import com.pm.payment.dto.PaymentResponseDTO;

public interface PaymentService {

    PaymentResponseDTO pay(PaymentRequestDTO requestDTO);
}
