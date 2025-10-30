package com.backend.jjj.cinema_api.mapper;

import com.backend.jjj.cinema_api.dto.tickets.RequestTicket;
import com.backend.jjj.cinema_api.dto.tickets.ResponseTicket;
import com.backend.jjj.cinema_api.models.TicketsModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketsMapper {
    @Mapping(target = "session.sessionId",source = "sessionId")
    @Mapping(target = "seat.seatId",source = "seatId")
    TicketsModel toEntity(RequestTicket request);
    @Mapping(target = "movieName",source = "session.movie.title")
    @Mapping(target = "sessionStart",source = "session.startTime")
    @Mapping(target = "seatRow",source = "seat.seatRow")
    @Mapping(target = "seatColumn",source = "seat.seatColumn")
    ResponseTicket toDto(TicketsModel ticketsModel);
}
