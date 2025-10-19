package com.backend.jjj.cinema_api.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record RequestUser(@NotBlank(message = "Nome não pode ser vazio") String name, @NotBlank(message = "Senha não pode ser vazia") String password, @NotBlank(message = "Email não pode ser vazio") @Email(message = "Não esta em formato de email valido") String email,
                          LocalDate birthday) {
}
