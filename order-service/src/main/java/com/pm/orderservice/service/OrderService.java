package com.pm.orderservice.service;

import com.pm.orderservice.dto.OrderResponseDTO;
import com.pm.orderservice.dto.PlaceOrderRequestDTO;

public interface OrderService {

    OrderResponseDTO placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO);
}
