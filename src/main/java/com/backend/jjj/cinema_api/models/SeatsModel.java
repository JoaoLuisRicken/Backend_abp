package com.backend.jjj.cinema_api.models;

import com.backend.jjj.cinema_api.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_seats")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeatsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String seatId;
    private String seatRow;
    private String seatColumn;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "roomId",name = "roomId")
    private RoomsModel room;
}
