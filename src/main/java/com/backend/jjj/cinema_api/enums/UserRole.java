package com.backend.jjj.cinema_api.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE"),
    CLIENT("CLIENT");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

}
