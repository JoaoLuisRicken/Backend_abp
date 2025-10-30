package com.backend.jjj.cinema_api.services;

import com.backend.jjj.cinema_api.dto.movies.RequestMovie;
import com.backend.jjj.cinema_api.dto.movies.RequestMovieUpdate;
import com.backend.jjj.cinema_api.dto.movies.ResponseMovie;
import com.backend.jjj.cinema_api.error.InactiveMovieException;
import com.backend.jjj.cinema_api.mapper.MoviesMapper;
import com.backend.jjj.cinema_api.models.MoviesModel;
import com.backend.jjj.cinema_api.repository.MoviesRepository;
import com.backend.jjj.cinema_api.specifications.MoviesSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoviesService {
    private final MoviesRepository moviesRepository;
    private final MoviesMapper moviesMapper;
    private final MinioService minioService;

    public ResponseMovie addMovie(RequestMovie request) {
        MoviesModel movie = moviesMapper.toEntity(request);
        if(request.image() != null && !request.image().isEmpty()){
            movie.setImageUrl(minioService.uploadFile(request.image()));
        }
        if(request.movie() != null && !request.movie().isEmpty()){
            movie.setMovieUrl(minioService.uploadFile(request.movie()));
        }
        return moviesMapper.toDto(moviesRepository.save(movie));
    }

    public ResponseMovie updateMovie(String movieId, RequestMovieUpdate request) {
        MoviesModel movie = getMovieById(movieId);
        moviesMapper.updateMovie(request, movie);
        if(request.image() != null && !request.image().isEmpty()){
            movie.setImageUrl(minioService.uploadFile(request.image()));
        }
        if(request.movie() != null && !request.movie().isEmpty()){
            movie.setMovieUrl(minioService.uploadFile(request.movie()));
        }
        return moviesMapper.toDto(moviesRepository.save(movie));
    }

    public void deleteMovie(String movieId) {
        MoviesModel movie = getMovieById(movieId);
        moviesRepository.delete(movie);
    }

    public ResponseMovie getMovie(String movieId){
        return moviesMapper.toDto(getMovieById(movieId));
    }

    public Page<ResponseMovie> getMovies(Pageable pageable,String title, List<String> genres){
        Specification<MoviesModel> spec = Specification.allOf(
                MoviesSpecification.isActive(),
                MoviesSpecification.titleContains(title),
                MoviesSpecification.hasAnyGenre(genres)
        );

        return moviesRepository.findAll(spec,pageable).map(moviesMapper::toDto);
    }

    public ResponseMovie outOfExibition(String movieId){
        MoviesModel movie = getMovieById(movieId);
        if(movie.getActive()){
            movie.setActive(false);
        }else{
            throw new InactiveMovieException("O filme não esta mais em exibição");
        }
        return moviesMapper.toDto(moviesRepository.save(movie));
    }

    private MoviesModel getMovieById(String id){
        return moviesRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Não foi possivel encontrar o filme"));
    }

}
