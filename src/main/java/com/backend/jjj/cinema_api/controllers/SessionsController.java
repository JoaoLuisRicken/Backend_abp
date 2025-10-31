package com.backend.jjj.cinema_api.controllers;

import com.backend.jjj.cinema_api.dto.sessions.RequestSession;
import com.backend.jjj.cinema_api.dto.sessions.ResponseSession;
import com.backend.jjj.cinema_api.services.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public Page<ResponseSession> getMovie(@PathVariable("movieId") String movieId,@RequestParam(required = false)LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "startTime") String sortBy,
            @RequestParam(defaultValue = "asc") String direction){
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return sessionService.getSessionsByMovie(movieId,date,pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseSession createSession(@RequestBody  @Valid  RequestSession session){
        return  sessionService.addSession(session);
    }
}
