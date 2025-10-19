package com.backend.jjj.cinema_api.dto.seats;

import com.backend.jjj.cinema_api.enums.SeatType;

public record ResponseSeats(String roomName, String seatRow, String seatColumn, SeatType seatType) {
}
