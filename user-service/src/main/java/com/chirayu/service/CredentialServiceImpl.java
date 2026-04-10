package com.chirayu.service;

import com.chirayu.exception.ResourceNotFoundException;
import com.chirayu.models.Credential;
import com.chirayu.repository.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService{

    private final CredentialRepository credentialRepository;

    @Override
    public Optional<Credential> findByUserName(String username) {
        return credentialRepository.findByUsername(username);
    }

    @Override
    public Optional<Credential> findByUsernameAndaPassword(String username, String password) {
        return credentialRepository.findByUsernameAndPassword(username,password);
    }


}
