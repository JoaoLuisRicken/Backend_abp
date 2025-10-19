package com.backend.jjj.cinema_api.repository;

import com.backend.jjj.cinema_api.models.SeatsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatsRepository extends JpaRepository<SeatsModel,String> {
}
