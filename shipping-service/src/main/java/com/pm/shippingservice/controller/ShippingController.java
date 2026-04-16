package com.pm.shippingservice.controller;

import com.pm.shippingservice.dto.ShippingRequestDTO;
import com.pm.shippingservice.dto.ShippingResponseDTO;
import com.pm.shippingservice.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
public class ShippingController {
    private final ShippingService shippingService;

    @PostMapping("/order-shipping")
    @ResponseStatus(HttpStatus.CREATED)
    public ShippingResponseDTO shippingOrder(@RequestBody ShippingRequestDTO requestDTO) {
       return shippingService.shippingOrder(requestDTO);
    }
}
