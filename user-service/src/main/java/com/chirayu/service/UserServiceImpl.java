package com.chirayu.service;

import com.chirayu.dto.CreateUserRequest;
import com.chirayu.dto.UserDto;
import com.chirayu.dto.UserUpdateRequest;
import com.chirayu.exception.DuplicateResourceException;
import com.chirayu.exception.ResourceNotFoundException;
import com.chirayu.mapper.CredentialMapper;
import com.chirayu.mapper.UserMapper;
import com.chirayu.models.Credential;
import com.chirayu.models.RoleBaseAuthority;
import com.chirayu.models.User;
import com.chirayu.repository.CredentialRepository;
import com.chirayu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;
    private final UserMapper userMapper;
    private final CredentialMapper credentialMapper;

    @Override
    public UserDto save(CreateUserRequest request) {
        if(userRepository.existsByEmail(request.getUserDto().getEmailAddress())){
            throw new DuplicateResourceException("Email is already used");
        }
        User user = userMapper.toEntity(request.getUserDto());
        User saveUser = userRepository.save(user);
        Credential credential = credentialMapper.toEntity(request.getCredentialDto());
        if(credential.getRole()==null){
            credential.setRole(RoleBaseAuthority.USER);
        }
        credential.setUserId(saveUser.getUserId());
        Credential saveCredential = credentialRepository.save(credential);
        saveUser.setCredentialId(saveCredential.getCredentialId());
        userRepository.save(saveUser);
        return userMapper.toDto(saveUser);
    }

    @Override
    public UserDto findById(String userID) {
        return userRepository.findById(userID)
                .map(userMapper::toDto)
                .orElseThrow(()->new ResourceNotFoundException("User not found with ID:: "+userID));
    }

    @Override
    @Transactional
    public UserDto update(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with ID:: " + userId));

        Credential credential = credentialRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found for userID:: " + userId));


        if(request.getFirstName()!=null){
            user.setFirstName(request.getFirstName());
        }
        if(request.getLastName()!=null){
            user.setLastName(request.getLastName());
        }
        if(request.getEmail()!=null){
            boolean existByEmailId = userRepository.findByEmail(request.getEmail())
                    .isPresent();
            if(existByEmailId && !user.getEmail().equals(request.getEmail())){
                throw new DuplicateResourceException("Email already in use: "+request.getEmail());
            }
            user.setEmail(request.getEmail());
        }
        if(request.getPhone()!=null){
            user.setPhone(request.getPhone());
        }
        if(request.getUsername()!=null){
            credential.setUsername(request.getUsername());
        }
        if(request.getPassword()!=null){
            credential.setPassword(request.getPassword());
        }
        if (request.getRole()!=null){
            credential.setRole(request.getRole());
        }
        userRepository.save(user);
        credentialRepository.save(credential);
        return null;
    }

    @Override
    public void delete(String userId) {

    }

}
