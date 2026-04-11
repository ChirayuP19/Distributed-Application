package com.chirayu.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(value = "PRODUCT-SERVICE",path = "/api/v1/products")
public interface ProductFeignClient {

    @GetMapping("/exits/{productId}")
    public Boolean isExistById(@PathVariable("productId") Long productId);

    @GetMapping("/price/{productId}")
    public BigDecimal productPriceById(@PathVariable("productId")Long productId);

}
