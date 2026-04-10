package com.chirayu.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "credential")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Credential {
    @Id
    private String credentialId;
    @Indexed(unique = true)
    @NotBlank(message = "username can't be blank.")
    @Size(min = 2,max = 50, message = "username must be between 2 and 50 characters" )
    private String username;
    @NotBlank(message = "password can't be blank.")
    @Size(min = 5,max = 50, message = "password must be between 5 and 50 characters" )
    private String password;
    private RoleBaseAuthority role;
    private String userId;
}
