package com.pm.orderservice.feignClients;

import com.pm.orderservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "USER-SERVICE",path = "/api/v1/user")
public interface UserFeignClient {

    @GetMapping("/exist/{userId}")
     Boolean existById(@PathVariable("userId") String userId);

    @GetMapping("/{userId}")
    UserDto findById(@PathVariable("userId") String userId);

}
