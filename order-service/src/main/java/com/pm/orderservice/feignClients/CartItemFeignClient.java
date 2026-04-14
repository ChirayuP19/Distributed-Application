package com.pm.orderservice.feignClients;

import com.pm.orderservice.dto.CartItemResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cart-service",path = "/api/v1/carts")
public interface CartItemFeignClient {

    @GetMapping("/{userId}")
    public List<CartItemResponseDto> getCartByUserID
            (@PathVariable("userId") String userId);

    @DeleteMapping("/clear/{userId}")
    public void clearUserCart(@PathVariable("userId") String  userId);

}

