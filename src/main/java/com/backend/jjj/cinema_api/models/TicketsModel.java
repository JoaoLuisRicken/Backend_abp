package com.backend.jjj.cinema_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_tickets")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "sessionId",name = "sessionId")
    private SessionsModel session;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "seatId",name = "seatId")
    private SeatsModel seat;
}
