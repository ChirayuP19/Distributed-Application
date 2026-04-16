package com.pm.shippingservice.service;

import com.pm.shippingservice.dto.*;
import com.pm.shippingservice.entity.ShippingInfo;
import com.pm.shippingservice.feignClients.OrderClient;
import com.pm.shippingservice.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository shippingRepository;
    private final OrderClient orderClient;

    @Override
    public ShippingResponseDTO shippingOrder(ShippingRequestDTO requestDTO) {
        OrderResponseDTO order = orderClient.getOrderById(requestDTO.getOrderId());
        if (order == null) {
            throw new IllegalArgumentException("Order Not Found with ID: " + requestDTO.getOrderId());
        }
        ShippingInfo shipping = ShippingInfo.builder()
                .orderId(requestDTO.getOrderId())
                .shippingMethod(requestDTO.getShippingMethod())
                .carrier(requestDTO.getCarrier())
                .status(OrderStatus.SHIPPED.toString())
                .shippingAt(LocalDateTime.now())
                .deliveryAt(requestDTO.getDeliveryType().calculateEstimatedTime())
                .build();
        ShippingInfo savedShipping = shippingRepository.save(shipping);
        orderClient.updateOrderStatus(requestDTO.getOrderId(), UpdateStatusRequest.builder()
                .status(OrderStatus.SHIPPED)
                .build());
        return toDto(savedShipping);
    }

    public ShippingResponseDTO toDto(ShippingInfo shipping) {
        return ShippingResponseDTO.builder()
                .orderId(shipping.getOrderId())
                .status(shipping.getStatus())
                .deliveryAt(shipping.getDeliveryAt())
                .shippingAt(shipping.getShippingAt())
                .carrier(shipping.getCarrier())
                .build();
    }
}
