package com.backend.jjj.cinema_api.controllers;

import com.backend.jjj.cinema_api.dto.movies.RequestMovie;
import com.backend.jjj.cinema_api.dto.movies.ResponseMovie;
import com.backend.jjj.cinema_api.services.MoviesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
@RequiredArgsConstructor
public class MoviesController {
    private final MoviesService moviesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMovie addMovie(@ModelAttribute @Valid RequestMovie requestMovie) throws Exception{
        return moviesService.addMovie(requestMovie);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getMovies(){
        return "Ola mundo";
    }
}
