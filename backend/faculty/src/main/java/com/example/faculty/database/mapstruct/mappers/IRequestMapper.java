package com.example.faculty.database.mapstruct.mappers;

import com.example.faculty.database.dto.request.RequestCreateDto;
import com.example.faculty.database.dto.request.RequestResponseDto;
import com.example.faculty.database.dto.request.RequestUpdateDto;
import com.example.faculty.database.entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {IUserMapper.class, ISubjectMapper.class, IEventMapper.class})
public interface IRequestMapper {

    IRequestMapper MAPPER = Mappers.getMapper(IRequestMapper.class);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "eventId", target = "event.id")
    @Mapping(source = "subjectId", target = "subject.id")
    Request createToRequest(RequestCreateDto dto);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "eventId", target = "event.id")
    @Mapping(source = "subjectId", target = "subject.id")
    Request updateToRequest(RequestUpdateDto dto);

    RequestResponseDto requestToResponse(Request request);
}

