package com.backend.jjj.cinema_api.services;

import com.backend.jjj.cinema_api.dto.movies.ResponseMovie;
import com.backend.jjj.cinema_api.dto.snapshot.ResponseSnapshotMovie;
import com.backend.jjj.cinema_api.mapper.MoviesMapper;
import com.backend.jjj.cinema_api.mapper.MoviesSnapshotsMapper;
import com.backend.jjj.cinema_api.models.MoviesModel;
import com.backend.jjj.cinema_api.models.MoviesSnapshotsModel;
import com.backend.jjj.cinema_api.repository.MoviesRepository;
import com.backend.jjj.cinema_api.repository.MoviesSnapshotsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoviesSnapshotsServices {
    private final MoviesSnapshotsRepository moviesSnapshotsRepository;
    private final MoviesSnapshotsMapper moviesSnapshotsMapper;
    private final MoviesMapper moviesMapper;
    private final MoviesRepository moviesRepository;
    private final MinioService minioService;

    public Page<ResponseSnapshotMovie> getSnapshots(Pageable pageable){
        return moviesSnapshotsRepository.findAll(pageable)
                .map(moviesSnapshotsMapper::toDto);
    }

    public ResponseSnapshotMovie getSnapshot(String id) {
        MoviesSnapshotsModel snapshot = moviesSnapshotsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Snapshot não encontrado"));

        return moviesSnapshotsMapper.toDto(snapshot);
    }

    public ResponseSnapshotMovie getSnapshotbyTitle(String title) {
        MoviesSnapshotsModel snapshot = moviesSnapshotsRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Snapshot não encontrado"));

        return moviesSnapshotsMapper.toDto(snapshot);
    }

    protected void saveSnapshot(MoviesModel movie){
        MoviesSnapshotsModel snapshot = moviesSnapshotsMapper.toSnapshot(movie);

        moviesSnapshotsRepository.save(snapshot);

    }

    private ResponseMovie recoverMovie(String snapshotId){
        MoviesSnapshotsModel  snapshot = moviesSnapshotsRepository.findById(snapshotId).
                orElseThrow(() -> new RuntimeException("Snapshot não encontrado"));
        MoviesModel movie = snapshot.getMovie();
        moviesMapper.updateMovieFromSnapshot(snapshot, movie);
        return moviesMapper.toDto(moviesRepository.save(movie),minioService);

    }
}
