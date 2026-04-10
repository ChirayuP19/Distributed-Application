package com.chirayu.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @Valid
    @NotNull(message = "User data is required")
    private UserDto userDto;
    @Valid
    @NotNull(message = "User data is required")
    private CredentialDto credentialDto;
}
