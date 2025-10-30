package com.backend.jjj.cinema_api.dto.tickets;

import java.time.LocalDateTime;

public record ResponseTicket(String ticketId,String movieName, LocalDateTime sessionStart,String seatRow,String seatColumn) {
}
