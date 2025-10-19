package com.backend.jjj.cinema_api.mapper;

import com.backend.jjj.cinema_api.dto.rooms.RequestRoom;
import com.backend.jjj.cinema_api.dto.rooms.ResponseRoom;
import com.backend.jjj.cinema_api.models.RoomsModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RoomsMapper {
    RoomsModel toEntity(RequestRoom request);
    ResponseRoom toResponse(RoomsModel room);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRoom(RequestRoom request, @MappingTarget RoomsModel response);
}
