package com.backend.jjj.cinema_api.repository;

import com.backend.jjj.cinema_api.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository<UsersModel,String> {
    UsersModel findByEmail(String email);
}
