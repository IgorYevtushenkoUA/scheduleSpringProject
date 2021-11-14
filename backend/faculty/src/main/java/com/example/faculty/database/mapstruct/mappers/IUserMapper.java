package com.example.faculty.database.mapstruct.mappers;

import com.example.faculty.database.dto.UserProfileDto;
import com.example.faculty.database.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserMapper {
    IUserMapper MAPPER = Mappers.getMapper(IUserMapper.class);

    UserProfileDto userToUserProfileDto(User user);
}
