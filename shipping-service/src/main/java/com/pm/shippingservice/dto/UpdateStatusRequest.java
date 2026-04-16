package com.pm.shippingservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateStatusRequest {
    @NotNull
        private OrderStatus status;
}
