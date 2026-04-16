package com.pm.shippingservice.feignClients;

import com.pm.shippingservice.dto.OrderResponseDTO;
import com.pm.shippingservice.dto.UpdateStatusRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "order-service", path = "/api/v1/orders")
public interface OrderClient {

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrderById(@PathVariable("orderId") Long orderId);

    @PostMapping("/update/status/{orderId}")
    public OrderResponseDTO updateOrderStatus(@PathVariable("orderId")Long orderId,
                                              @RequestBody UpdateStatusRequest orderStatus);

}
