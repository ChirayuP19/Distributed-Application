package com.chirayu.repository;

import com.chirayu.models.Credential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends MongoRepository<Credential,String> {
    Optional<Credential> findByUserId(String userId);
    Optional<Credential> findByUsername(String username);
    Optional<Credential> findByUsernameAndPassword(String username,String password);
}
