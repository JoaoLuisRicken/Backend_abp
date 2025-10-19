package com.backend.jjj.cinema_api.enums;

import lombok.Getter;

@Getter
public enum SeatType {
    NORMAL("NORMAL");
    private final String type;
    SeatType(String type) {
        this.type = type;
    }
}
