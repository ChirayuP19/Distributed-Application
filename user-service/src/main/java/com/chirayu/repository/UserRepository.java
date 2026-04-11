package com.chirayu.repository;

import com.chirayu.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String emailId);
    Boolean existsByEmail(String email);
    Boolean existsByUserId(String userId);
}
