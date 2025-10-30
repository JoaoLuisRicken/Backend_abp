package com.backend.jjj.cinema_api.dto.sessions;

import com.backend.jjj.cinema_api.enums.SessionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RequestSession(
        @NotBlank(message = "Filme não pode ser nulo") String movieId,
        String roomId,
        @NotNull(message = "Horário de início não pode ser nulo") LocalDateTime startTime,
        @NotNull(message = "Tipo de sessão não pode ser nulo") SessionType type
) {
}
