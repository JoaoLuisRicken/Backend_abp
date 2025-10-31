package com.backend.jjj.cinema_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoviesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String movieId;
    private String title;
    private String description;
    private Integer ageRating;
    private Integer duration;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> genres;
    private Boolean active = true;
    private String imageUrl;
    private String movieUrl;
}
