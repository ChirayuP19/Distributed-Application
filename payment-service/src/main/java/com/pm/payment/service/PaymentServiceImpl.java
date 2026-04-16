package com.pm.payment.service;

import com.pm.payment.dto.*;
import com.pm.payment.feignclients.OrderClient;
import com.pm.payment.feignclients.UserFeignClient;
import com.pm.payment.model.Payment;
import com.pm.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;
    private final UserFeignClient userFeignClient;

    @Override
    public PaymentResponseDTO pay(PaymentRequestDTO requestDTO) {
        OrderResponseDTO order = orderClient.getOrderById(requestDTO.getOrderId());
        UserDto userDto = userFeignClient.findById(requestDTO.getUserId());
        if(order == null  || userDto == null) {
            throw new IllegalArgumentException("User or Order Id is not found");
        }
        if (!order.getUserId().equals(requestDTO.getUserId())) {
            throw new IllegalArgumentException("Order does not belong to user");
        }
        if (order.getTotalPrice().compareTo(requestDTO.getAmount()) != 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        Payment payment = Payment.builder()
                .orderId(requestDTO.getOrderId())
                .userId(requestDTO.getUserId())
                .amount(requestDTO.getAmount())
                .paymentDate(LocalDateTime.now())
                .status("SUCCESS")
                .build();
        Payment saved = paymentRepository.save(payment);
        if ("SUCCESS".equals(saved.getStatus())) {
            UpdateStatusRequest statusRequest = UpdateStatusRequest.builder()
                    .status(OrderStatus.PAID)
                    .build();
            orderClient.updateOrderStatus(saved.getOrderId(), statusRequest);
        }
        return toDto(saved);
    }
    private PaymentResponseDTO toDto(Payment payment){
        return PaymentResponseDTO.builder()
                .orderId(payment.getOrderId())
                .paymentId(payment.getId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .paymentDate(payment.getPaymentDate())
                .build();
    }

}
