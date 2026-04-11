package com.chirayu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CredentialDto {

    @NotBlank(message = "Username can't be blank")
    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    private String username;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 5, max = 50, message = "Password must be between 5 and 50 characters")
    private String password;

    private RoleBaseAuthority role;
}