package com.backend.jjj.cinema_api.repository;

import com.backend.jjj.cinema_api.models.TicketsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepository extends JpaRepository<TicketsModel,String> {
}
