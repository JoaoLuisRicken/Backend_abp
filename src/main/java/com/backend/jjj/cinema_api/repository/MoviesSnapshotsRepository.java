package com.backend.jjj.cinema_api.repository;



import com.backend.jjj.cinema_api.models.MoviesSnapshotsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface MoviesSnapshotsRepository extends JpaRepository<MoviesSnapshotsModel, String> {
        Page<MoviesSnapshotsModel> findAll(Pageable pageable);
        Optional<MoviesSnapshotsModel> findByTitle(String title);


    }


