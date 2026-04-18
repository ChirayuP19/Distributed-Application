package com.pm.payment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phone;
    private String credentialId;
    private String errorMessage;
}
