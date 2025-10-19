package com.backend.jjj.cinema_api.dto.movies;

import java.util.List;

public record ResponseMovie(String movieId, String title, String description, Integer ageRating, List<String> genres, String imageUrl) {
}
