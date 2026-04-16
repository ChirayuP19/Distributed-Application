package com.pm.payment.controller;

import com.pm.payment.dto.PaymentRequestDTO;
import com.pm.payment.dto.PaymentResponseDTO;
import com.pm.payment.repository.PaymentRepository;
import com.pm.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PaymentResponseDTO pay(@RequestBody PaymentRequestDTO requestDTO) {
        return paymentService.pay(requestDTO);
    }
}
