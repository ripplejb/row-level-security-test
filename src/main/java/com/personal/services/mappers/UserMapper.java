package com.personal.services.mappers;

import com.personal.models.UserCredential;
import com.personal.models.dtos.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(UserCredential userCredential);

    @InheritInverseConfiguration
    UserCredential userDtoToUser(UserDto user);
}
