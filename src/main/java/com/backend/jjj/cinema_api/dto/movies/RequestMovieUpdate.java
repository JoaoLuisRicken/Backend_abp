package com.backend.jjj.cinema_api.dto.movies;


import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record RequestMovieUpdate(
        String title,
        String description,
        List<String> genres,
        Integer ageRating,
        MultipartFile image,
        MultipartFile movie
) {
}
