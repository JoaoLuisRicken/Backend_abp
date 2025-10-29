package com.backend.jjj.cinema_api.mapper;

import com.backend.jjj.cinema_api.dto.seats.RequestSeats;
import com.backend.jjj.cinema_api.dto.seats.ResponseSeats;
import com.backend.jjj.cinema_api.models.SeatsModel;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SeatsMapper {
    @Mapping(source = "roomId",target = "room.roomId")
    SeatsModel toEntity(RequestSeats requestSeats);
    @Mapping(source = "room.name",target = "roomName")
    ResponseSeats toDto(SeatsModel seatsModel);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSeat(RequestSeats request,@MappingTarget SeatsModel seatsModel);
}
