package com.pm.orderservice.feignClients;

import com.pm.orderservice.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(value = "PRODUCT-SERVICE",path = "/api/v1/products")
public interface ProductFeignClient {

    @GetMapping("/exits/{productId}")
     Boolean isExistById(@PathVariable("productId") Long productId);

    @GetMapping("/price/{productId}")
     BigDecimal productPriceById(@PathVariable("productId")Long productId);

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
     ProductResponseDto findById(@PathVariable("productId") Long productId);

    @PostMapping("/batch")
    List<ProductResponseDto> findAllByIds(@RequestBody List<Long> ids);

}
