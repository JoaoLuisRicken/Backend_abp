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
import com.backend.jjj.cinema_api.specifications.SessionsSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    private MoviesModel getMovies(String movieId){
        return moviesRepository.findById(movieId).get();
    }

    private boolean roomExists(String roomId){
        return roomsRepository.findById(roomId).isPresent();
    }

    private SessionsModel getSessionById(String sessionId){
        return sessionsRepository.findById(sessionId).orElseThrow(()-> new EntityNotFoundException("Sessão não encontrada"));
    }

    public ResponseSession addSession(RequestSession request){
        if(request.startTime().isBefore(LocalDateTime.now())){
            throw new InvalidTimeException("O filme não pode ser cadastrado no passado");
        }
        SessionsModel session = sessionsMapper.toEntity(request);
        if(!movieExists(request.movieId())){
            throw new EntityNotFoundException("O filme não foi encontrado");
        }
        MoviesModel movie = getMovies(request.movieId());
        if(request.type() == SessionType.PRESENCIAL) {
            if(request.roomId() == null || request.roomId().isEmpty()){
                throw new RuntimeException("O id da sala não pode ser nulo em sessões presenciais");
            }
            if(!roomExists(request.roomId())) {
                throw new EntityNotFoundException("A sala não foi encontrada");
            }
        }else{
            session.setRoom(null);
            if(movie.getMovieUrl() == null){
                throw new RuntimeException("O filme não é valido para seções online pois não tem um filme online.");
            }
        }
        LocalDateTime endTime = session.getStartTime().plusMinutes(movie.getDuration());
        session.setEndTime(endTime);
        return sessionsMapper.toDto(sessionsRepository.save(session));
    }

    public Page<ResponseSession> getSessionsByMovie(String movieId, LocalDate date, Pageable pageable){
        Specification<SessionsModel> spec = Specification.allOf(SessionsSpecification.hasDate(date),SessionsSpecification.hasMovieId(movieId),SessionsSpecification.isActive());
        return sessionsRepository.findAll(spec,pageable).map(sessionsMapper::toDto);
    }

    public ResponseSession getSession(String sessionId){
        return sessionsMapper.toDto(getSessionById(sessionId));
    }
}
