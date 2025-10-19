package com.backend.jjj.cinema_api.dto.rooms;

import jakarta.validation.constraints.NotBlank;

public record RequestRoom(@NotBlank(message = "Nome não pode ser vazio") String name) {
}
