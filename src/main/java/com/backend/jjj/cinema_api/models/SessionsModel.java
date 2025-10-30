package com.backend.jjj.cinema_api.models;

import com.backend.jjj.cinema_api.enums.SessionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_sessions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessionsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String sessionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "movieId",name = "movieId")
    private MoviesModel movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "roomId",name = "roomId")
    private RoomsModel room;
    @Enumerated(EnumType.STRING)
    private SessionType type;
    private LocalDateTime startTime;
}
