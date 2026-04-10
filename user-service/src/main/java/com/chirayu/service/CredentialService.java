package com.chirayu.service;

import com.chirayu.models.Credential;

import java.util.Optional;

public interface CredentialService {
    Optional<Credential> findByUserName(String username);
    Optional<Credential> findByUsernameAndaPassword(String username,String password);
}
