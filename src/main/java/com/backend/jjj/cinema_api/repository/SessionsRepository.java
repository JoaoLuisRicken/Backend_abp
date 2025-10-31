package com.backend.jjj.cinema_api.repository;

import com.backend.jjj.cinema_api.models.SessionsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SessionsRepository extends JpaRepository<SessionsModel, String>, JpaSpecificationExecutor<SessionsModel> {
    Page<SessionsModel> findAll(Pageable pageable);

}
