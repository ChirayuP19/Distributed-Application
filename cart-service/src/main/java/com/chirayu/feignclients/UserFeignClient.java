package com.chirayu.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "USER-SERVICE",path = "/api/v1/user")
public interface UserFeignClient {

    @GetMapping("/exist/{userId}")
    public Boolean existById(@PathVariable("userId") String userId);


}
