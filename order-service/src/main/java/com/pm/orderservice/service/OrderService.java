package com.pm.orderservice.service;

import com.pm.orderservice.dto.OrderResponseDTO;
import com.pm.orderservice.dto.OrderStatus;
import com.pm.orderservice.dto.PlaceOrderRequestDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    OrderResponseDTO placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO);

    OrderResponseDTO updateStatus(Long orderId, OrderStatus orderStatus);

    Page<OrderResponseDTO> getAllOrders(int page, int size, String sortBy, String direction);
}
