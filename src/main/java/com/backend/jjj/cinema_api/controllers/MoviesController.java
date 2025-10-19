package com.backend.jjj.cinema_api.controllers;

import com.backend.jjj.cinema_api.dto.movies.RequestMovie;
import com.backend.jjj.cinema_api.dto.movies.RequestMovieUpdate;
import com.backend.jjj.cinema_api.dto.movies.ResponseMovie;
import com.backend.jjj.cinema_api.services.MoviesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<ResponseMovie> getMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return moviesService.getMovies(pageable);
    }

    @GetMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMovie getMovie(@PathVariable String movieId){
        return moviesService.getMovie(movieId);
    }

    @PatchMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMovie updateMovie(@PathVariable String movieId,@ModelAttribute RequestMovieUpdate requestMovie){
        return moviesService.updateMovie(movieId,requestMovie);
    }

    @PatchMapping("/out/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMovie outMovie(@PathVariable String movieId){
        return moviesService.outOfExibition(movieId);
    }

    @DeleteMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMovie(@PathVariable String movieId){
        moviesService.deleteMovie(movieId);
    }

}
