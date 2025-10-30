package com.backend.jjj.cinema_api.controllers;

import com.backend.jjj.cinema_api.dto.sessions.RequestSession;
import com.backend.jjj.cinema_api.dto.sessions.ResponseSession;
import com.backend.jjj.cinema_api.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sessions")
@RequiredArgsConstructor
public class SessionsController {
    private  final SessionService sessionService;

    @GetMapping("/{sessionId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSession getSession(@PathVariable("sessionId") String sessionId){
        return sessionService.getSession(sessionId);
    }

    @GetMapping("/movie/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseSession> getMovie(@PathVariable("movieId") String movieId){
        return sessionService.getSessionsByMovie(movieId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseSession createSession(@RequestBody RequestSession session){
        return  sessionService.addSession(session);
    }
}
