package com.backend.jjj.cinema_api.repository;

import com.backend.jjj.cinema_api.models.SeatsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatsRepository extends JpaRepository<SeatsModel,String> {
    List<SeatsModel> findAllByRoomRoomId(String roomId);
}
