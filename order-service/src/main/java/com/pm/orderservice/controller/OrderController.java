package com.pm.orderservice.controller;

import com.pm.orderservice.dto.OrderResponseDTO;
import com.pm.orderservice.dto.OrderStatus;
import com.pm.orderservice.dto.PlaceOrderRequestDTO;
import com.pm.orderservice.dto.UpdateStatusRequest;
import com.pm.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

    @PostMapping("/update/status/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDTO updateOrderStatus(@PathVariable("orderId")Long orderId,
                                              @RequestBody UpdateStatusRequest orderStatus){
        log.info("Received request to update order for ID:: {}",orderId);
        return orderService.updateStatus(orderId,orderStatus.getStatus());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderResponseDTO> getAllOrder(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "desc")String sortBy,
            @RequestParam(defaultValue = "id") String direction){
        log.info("Received request to get all orders");
        return orderService.getAllOrders(page,size,sortBy,direction);
    }

}
