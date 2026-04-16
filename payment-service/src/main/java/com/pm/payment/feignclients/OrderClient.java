package com.pm.payment.feignclients;

import com.pm.payment.dto.OrderResponseDTO;
import com.pm.payment.dto.UpdateStatusRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "order-service",path = "/api/v1/orders")
public interface OrderClient {

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrderById(@PathVariable("orderId")Long orderId);

    @PostMapping("/update/status/{orderId}")
    public OrderResponseDTO updateOrderStatus(@PathVariable("orderId")Long orderId,
                                              @RequestBody UpdateStatusRequest orderStatus);
}
