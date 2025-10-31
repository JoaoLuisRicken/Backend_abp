package com.backend.jjj.cinema_api.mapper;

import com.backend.jjj.cinema_api.dto.movies.RequestMovie;
import com.backend.jjj.cinema_api.dto.movies.RequestMovieUpdate;
import com.backend.jjj.cinema_api.dto.movies.ResponseMovie;
import com.backend.jjj.cinema_api.models.MoviesModel;
import com.backend.jjj.cinema_api.services.MinioService;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MoviesMapper {
    MoviesModel toEntity(RequestMovie request);
    @Mapping(target = "imageUrl",expression = "java(movie.getImageUrl() != null ? minioService.getFileUrl(movie.getImageUrl()) : null)")
    ResponseMovie toDto(MoviesModel movie,@Context MinioService minioService);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMovie(RequestMovieUpdate request, @MappingTarget MoviesModel movie);
}
