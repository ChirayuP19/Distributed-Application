package com.chirayu.controller;

import com.chirayu.exception.ResourceNotFoundException;
import com.chirayu.models.Credential;
import com.chirayu.service.CredentialService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/credential")
@RequiredArgsConstructor
public class CredntialController {

    private final CredentialService credentialService;

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Credential findByUsername(@PathVariable("username") String username){
        return credentialService.findByUserName(username)
                .orElseThrow(
                        ()->new ResourceNotFoundException("User not found with given username:: "+username));
    }

    @GetMapping("/{username}/{password}")
    @ResponseStatus(HttpStatus.OK)
    public Credential findByUsernameAndPassword (
            @PathVariable("username")String username,
            @PathVariable("password")String password){
        return credentialService.findByUsernameAndaPassword(username,password)
                .orElseThrow(()->new ResourceNotFoundException
                        (username+" or "+password+" Invalid not found any match Please try again .."));
    }
}
