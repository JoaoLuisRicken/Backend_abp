package com.backend.jjj.cinema_api.mapper;

import com.backend.jjj.cinema_api.dto.snapshot.ResponseSnapshotMovie;
import com.backend.jjj.cinema_api.models.MoviesModel;
import com.backend.jjj.cinema_api.models.MoviesSnapshotsModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MoviesSnapshotsMapper {
    MoviesSnapshotsModel toSnapshot(MoviesModel movie);
    ResponseSnapshotMovie toDto(MoviesSnapshotsModel snapshot);

}