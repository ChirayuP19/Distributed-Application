package com.chirayu.dto;

import com.chirayu.models.RoleBaseAuthority;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must be 10 digits"
    )
    private String phone;

    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    private String username;

    @Size(min = 5, max = 50, message = "Password must be between 5 and 50 characters")
    private String password;

    private RoleBaseAuthority role;
}