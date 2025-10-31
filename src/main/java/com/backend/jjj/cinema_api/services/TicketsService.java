package com.backend.jjj.cinema_api.services;

import com.backend.jjj.cinema_api.dto.tickets.RequestTicket;
import com.backend.jjj.cinema_api.dto.tickets.ResponseTicket;
import com.backend.jjj.cinema_api.enums.SessionType;
import com.backend.jjj.cinema_api.error.UnableSeatException;
import com.backend.jjj.cinema_api.mapper.TicketsMapper;
import com.backend.jjj.cinema_api.models.MoviesModel;
import com.backend.jjj.cinema_api.models.SeatsModel;
import com.backend.jjj.cinema_api.models.SessionsModel;
import com.backend.jjj.cinema_api.models.TicketsModel;
import com.backend.jjj.cinema_api.repository.SeatsRepository;
import com.backend.jjj.cinema_api.repository.SessionsRepository;
import com.backend.jjj.cinema_api.repository.TicketsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketsService {
    private final TicketsRepository ticketsRepository;
    private final SessionsRepository sessionsRepository;
    private final SeatsRepository seatsRepository;
    private final TicketsMapper ticketsMapper;
    private final MinioService  minioService;

    private SessionsModel getSessionById(String sessionId) {
        return sessionsRepository.findById(sessionId).orElseThrow(()->new RuntimeException("Sessão não encontrada"));
    }

    private SeatsModel getSeatById(String seatId) {
        return seatsRepository.findById(seatId).orElseThrow(()-> new EntityNotFoundException("Acento não encontrado"));
    }

    public ResponseTicket addTicket(RequestTicket request){
        SessionsModel session = getSessionById(request.sessionId());
        TicketsModel ticket = ticketsMapper.toEntity(request);
        if(session.getType()== SessionType.PRESENCIAL){
            SeatsModel seat = getSeatById(request.seatId());
            if(!seat.getActive()){
                throw new UnableSeatException("Acento não disponivel");
            }
            if(!ticketsRepository.findBySessionSessionIdAndSeatSeatId(request.sessionId(), request.seatId()).isEmpty()){
                throw new RuntimeException("O acento já esta ocupado");
            }
        }else{
            ticket.setSeat(null);
        }
        ticket.setUser(AuthorizationService.getAccount());
        return ticketsMapper.toDto(ticketsRepository.save(ticket));
    }

    public List<ResponseTicket> getTickets(){
        return ticketsRepository.findByUser(AuthorizationService.getAccount()).stream().map(ticketsMapper::toDto).toList();
    }

    public ResponseTicket getTicket(String ticketId){
        return ticketsMapper.toDto(getTicketById(ticketId));
    }

    private TicketsModel getTicketById(String ticketId){
        return ticketsRepository.findById(ticketId).orElseThrow(()-> new EntityNotFoundException("Ingresso não encontrado"));
    }

    public InputStream streamSession(String ticketId) throws IOException {
        TicketsModel ticket = getTicketById(ticketId);
        LocalDateTime now  = LocalDateTime.now();
        SessionsModel session = ticket.getSession();
        if(session.getType()== SessionType.PRESENCIAL){
            throw new RuntimeException("Este ingresso é para uma sessão presencial");
        }
        if(session.getStartTime().isAfter(now)){
            throw new RuntimeException("O filme ainda não começou");
        }
        if(session.getEndTime().isBefore(now)){
            throw new RuntimeException("O filme já acabou");
        }
        MoviesModel movie = session.getMovie();
        return minioService.getObjectStream(movie.getMovieUrl());
    }
}
