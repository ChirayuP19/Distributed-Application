package com.chirayu.controller;

import com.chirayu.dto.CreateUserRequest;
import com.chirayu.dto.UserDto;
import com.chirayu.dto.UserUpdateRequest;
import com.chirayu.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/sign-up")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.save(request);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto findById(@PathVariable("userId") String userId) {
        return userService.findById(userId);
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUserDetails(
            @PathVariable("userId") String userId,
            @Valid @RequestBody UserUpdateRequest userRequest){
        return userService.update(userId,userRequest);
    }
}
