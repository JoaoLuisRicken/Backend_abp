package com.backend.jjj.cinema_api.enums;

import lombok.Getter;

@Getter
public enum SessionType {
    PRESENCIAL("PRESENCIAL"),
    ONLINE("ONLINE");
    private final String type;
    SessionType(String type) {
        this.type = type;
    }
}
