package com.backend.jjj.cinema_api.repository;

import com.backend.jjj.cinema_api.models.TicketsModel;
import com.backend.jjj.cinema_api.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketsRepository extends JpaRepository<TicketsModel,String> {
    List<TicketsModel> findBySessionIdAndSeatId(String sessionId,String seatId);
    List<TicketsModel> findByUser(UsersModel user);
}
