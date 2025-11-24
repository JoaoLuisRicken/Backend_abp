package com.backend.jjj.cinema_api.controllers;

import com.backend.jjj.cinema_api.dto.movies.ResponseMovie;
import com.backend.jjj.cinema_api.dto.snapshot.ResponseSnapshotMovie;
import com.backend.jjj.cinema_api.services.MoviesSnapshotsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/movies/snapshots")
@RequiredArgsConstructor
public class MoviesSnapshotsController {
    private final MoviesSnapshotsServices moviesSnapshotsServices;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponseSnapshotMovie> getSnapshots(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return moviesSnapshotsServices.getSnapshots(pageable);
    }

    @GetMapping("/{snapshotId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSnapshotMovie getSnapshot(@PathVariable String snapshotId) {
        return moviesSnapshotsServices.getSnapshot(snapshotId);
    }

    @GetMapping("/{snapshotTitle}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSnapshotMovie getSnapshotbyTitle(@PathVariable String snapshotTitle) {
        return moviesSnapshotsServices.getSnapshotbyTitle(snapshotTitle);
    }
}
