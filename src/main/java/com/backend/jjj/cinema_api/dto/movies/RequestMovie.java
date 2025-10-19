package com.backend.jjj.cinema_api.dto.movies;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record RequestMovie(

        @NotBlank(message = "O título é obrigatório")
        String title,
        @NotBlank(message = "A descrição é obrigatória")
        String description,
        @NotEmpty(message = "Deve ter pelo menos um gênero")
        List<@NotBlank(message = "Gênero não pode ser vazio") String> genres,
        @Min(value = 0, message = "Classificação etária mínima é 0")
        @Max(value = 18, message = "Classificação etária máxima é 18")
        Integer ageRating,
        MultipartFile image
) {}
