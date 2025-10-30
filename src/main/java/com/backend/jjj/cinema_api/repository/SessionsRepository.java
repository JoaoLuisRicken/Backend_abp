package com.backend.jjj.cinema_api.repository;

import com.backend.jjj.cinema_api.models.SessionsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SessionsRepository extends JpaRepository<SessionsModel, String> {
    List<SessionsModel> findAllByMovieId(String movieId);
}
