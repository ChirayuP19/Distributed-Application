package com.pm.shippingservice.service;

import com.pm.shippingservice.dto.ShippingRequestDTO;
import com.pm.shippingservice.dto.ShippingResponseDTO;

public interface ShippingService {
    ShippingResponseDTO shippingOrder(ShippingRequestDTO requestDTO);
}
