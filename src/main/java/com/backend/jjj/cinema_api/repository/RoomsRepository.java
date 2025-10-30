package com.backend.jjj.cinema_api.repository;

import com.backend.jjj.cinema_api.models.RoomsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<RoomsModel, String> {
    Page<RoomsModel> findAll(Pageable pageable);
    RoomsModel findByName(String name);
}
