package com.backend.jjj.cinema_api.dto.snapshot;

import com.backend.jjj.cinema_api.models.MoviesModel;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseSnapshotMovie(
        String movieSnapshotId, String title, String description, Integer ageRating, Integer duration,
        List<String> genres, String imageUrl, MoviesModel movie, LocalDateTime createdAt
) {

}
