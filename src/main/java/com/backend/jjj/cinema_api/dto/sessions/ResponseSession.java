package com.backend.jjj.cinema_api.dto.sessions;

import com.backend.jjj.cinema_api.enums.SessionType;

import java.time.LocalDateTime;

public record ResponseSession(String sessionId, String nameMovie, String nameRoom, LocalDateTime startTime,LocalDateTime endTime, SessionType type) {
}
