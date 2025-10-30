package com.backend.jjj.cinema_api.dto.users;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(@NotBlank(message = "O login deve incluir o usuario") String username,@NotBlank(message = "O login deve incluir a senha") String password) {
}
