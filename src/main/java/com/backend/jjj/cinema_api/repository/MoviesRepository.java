package com.backend.jjj.cinema_api.repository;

import com.backend.jjj.cinema_api.models.MoviesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MoviesRepository extends JpaRepository<MoviesModel,String>, JpaSpecificationExecutor<MoviesModel> {
    Page<MoviesModel> findAll(Pageable pageable);
}
