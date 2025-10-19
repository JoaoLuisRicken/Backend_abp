package com.backend.jjj.cinema_api.repository;

import com.backend.jjj.cinema_api.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersModel,String> {
}
