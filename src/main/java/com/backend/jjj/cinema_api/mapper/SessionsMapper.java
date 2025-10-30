package com.backend.jjj.cinema_api.mapper;

import com.backend.jjj.cinema_api.dto.sessions.RequestSession;
import com.backend.jjj.cinema_api.dto.sessions.ResponseSession;
import com.backend.jjj.cinema_api.models.SessionsModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionsMapper {
    @Mapping(source = "movieId",target = "movie.movieId")
    @Mapping(source = "roomId",target = "room.roomId")
    SessionsModel toEntity(RequestSession request);
    @Mapping(target = "nameMovie",source = "movie.title")
    @Mapping(target = "nameRoom",source = "room.name")
    ResponseSession toDto(SessionsModel session);
}
