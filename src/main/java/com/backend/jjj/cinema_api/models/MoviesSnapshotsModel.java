package com.backend.jjj.cinema_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_movies_snapshots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoviesSnapshotsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String movieSnapshotId;
    private String title;
    private String description;
    private Integer ageRating;
    private List<String> genres;
    private Boolean active;
    private String imageUrl;
    private String movieUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "movieId",name = "movieId")
    private MoviesModel movie;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
