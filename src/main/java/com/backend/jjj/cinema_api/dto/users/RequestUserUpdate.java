package com.backend.jjj.cinema_api.dto.users;

import jakarta.validation.constraints.Email;

public record RequestUserUpdate(String name, @Email(message = "Não esta em formato de email valido") String email) {
}
