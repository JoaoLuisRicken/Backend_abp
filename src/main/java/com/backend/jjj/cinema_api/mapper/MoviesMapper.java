package com.backend.jjj.cinema_api.mapper;

import com.backend.jjj.cinema_api.dto.movies.RequestMovie;
import com.backend.jjj.cinema_api.dto.movies.RequestMovieUpdate;
import com.backend.jjj.cinema_api.dto.movies.ResponseMovie;
import com.backend.jjj.cinema_api.models.MoviesModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MoviesMapper {
    MoviesModel toEntity(RequestMovie request);
    ResponseMovie toDto(MoviesModel movie);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMovie(RequestMovieUpdate request, @MappingTarget MoviesModel movie);
}
