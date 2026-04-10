package com.chirayu.mapper;

import com.chirayu.dto.CredentialDto;
import com.chirayu.models.Credential;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialMapper {
    CredentialDto toDto(Credential credential);
    Credential toEntity(CredentialDto credentialDto);
}
