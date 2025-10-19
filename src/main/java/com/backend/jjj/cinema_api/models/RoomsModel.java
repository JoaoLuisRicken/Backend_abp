package com.backend.jjj.cinema_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String roomId;
    private String name;
}
