package com.backend.jjj.cinema_api.services;

import com.backend.jjj.cinema_api.dto.sessions.RequestSession;
import com.backend.jjj.cinema_api.dto.sessions.ResponseSession;
import com.backend.jjj.cinema_api.enums.SessionType;
import com.backend.jjj.cinema_api.error.InvalidTimeException;
import com.backend.jjj.cinema_api.mapper.SessionsMapper;
import com.backend.jjj.cinema_api.models.MoviesModel;
import com.backend.jjj.cinema_api.models.SessionsModel;
import com.backend.jjj.cinema_api.repository.MoviesRepository;
import com.backend.jjj.cinema_api.repository.RoomsRepository;
import com.backend.jjj.cinema_api.repository.SessionsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final MoviesRepository moviesRepository;
    private final RoomsRepository roomsRepository;
    private final SessionsRepository sessionsRepository;
    private final SessionsMapper sessionsMapper;
    private boolean movieExists(String movieId){
        return moviesRepository.findById(movieId).isPresent();
    }

    private boolean roomExists(String roomId){
        return roomsRepository.findById(roomId).isPresent();
    }

    private SessionsModel getSessionById(String sessionId){
        return sessionsRepository.findById(sessionId).orElseThrow(()-> new EntityNotFoundException("Sessão não encontrada"));
    }

    private ResponseSession addSession(RequestSession request){
        if(request.startTime().isBefore(LocalDateTime.now())){
            throw new InvalidTimeException("O filme não pode ser cadastrado no passado");
        }
        if(movieExists(request.movieId())){
            if(request.type() == SessionType.PRESENCIAL && !roomExists(request.roomId())) {
                throw new EntityNotFoundException("A sala não foi encontrada");
            }
            return sessionsMapper.toDto(sessionsRepository.save(sessionsMapper.toEntity(request)));
        }else{
            throw new EntityNotFoundException("O filme não foi encontrado");
        }
    }

    private List<ResponseSession> getSessionsByMovie(String movieId){
        return sessionsRepository.findAllByMovieId(movieId).stream().map(sessionsMapper::toDto).toList();
    }
}
