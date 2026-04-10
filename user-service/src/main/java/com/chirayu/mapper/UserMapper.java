package com.chirayu.mapper;

import com.chirayu.dto.UserDto;
import com.chirayu.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "email",target = "emailAddress")
    UserDto toDto(User user);
    @Mapping(source = "emailAddress",target = "email")
    User toEntity(UserDto userDto);

}
