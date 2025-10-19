package com.backend.jjj.cinema_api.dto.users;

import com.backend.jjj.cinema_api.enums.UserRole;

import java.time.LocalDate;

public record ResponseUser(String userId, String name, String email, LocalDate birthday, UserRole role) {
}
