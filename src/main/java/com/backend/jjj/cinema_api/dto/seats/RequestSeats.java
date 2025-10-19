package com.backend.jjj.cinema_api.dto.seats;

import com.backend.jjj.cinema_api.enums.SeatType;
import jakarta.validation.constraints.NotBlank;

public record RequestSeats(@NotBlank(message = "O id da sala não pode ser nulo") String roomId,@NotBlank(message = "o assento deve ter a linha especificada") String seatRow,@NotBlank(message = "o assento deve ter a coluna especificada")  String seatColumn, SeatType seatType) {
}
