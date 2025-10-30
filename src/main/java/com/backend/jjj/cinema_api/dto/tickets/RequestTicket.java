package com.backend.jjj.cinema_api.dto.tickets;

import jakarta.validation.constraints.NotBlank;

public record RequestTicket(@NotBlank(message = "Sessão não pode ser nula") String sessionId, String seatId) {
}
