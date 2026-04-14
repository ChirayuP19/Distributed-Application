package com.pm.orderservice.controller;

import com.pm.orderservice.dto.OrderResponseDTO;
import com.pm.orderservice.dto.PlaceOrderRequestDTO;
import com.pm.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO placeOrder(@RequestBody PlaceOrderRequestDTO requestDTO){
        log.info("Received request to place order for ID:: {}",requestDTO.getUserId());
        return orderService.placeOrder(requestDTO);
    }

}
