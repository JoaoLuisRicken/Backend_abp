package com.backend.jjj.cinema_api.services;

import com.backend.jjj.cinema_api.dto.tickets.RequestTicket;
import com.backend.jjj.cinema_api.dto.tickets.ResponseTicket;
import com.backend.jjj.cinema_api.enums.SessionType;
import com.backend.jjj.cinema_api.error.UnableSeatException;
import com.backend.jjj.cinema_api.mapper.TicketsMapper;
import com.backend.jjj.cinema_api.models.SeatsModel;
import com.backend.jjj.cinema_api.models.SessionsModel;
import com.backend.jjj.cinema_api.models.TicketsModel;
import com.backend.jjj.cinema_api.repository.SeatsRepository;
import com.backend.jjj.cinema_api.repository.SessionsRepository;
import com.backend.jjj.cinema_api.repository.TicketsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketsService {
    private final TicketsRepository ticketsRepository;
    private final SessionsRepository sessionsRepository;
    private final SeatsRepository seatsRepository;
    private final TicketsMapper ticketsMapper;

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

    public ResponseTicket getTicketById(String ticketId){
        return ticketsMapper.toDto(ticketsRepository.findById(ticketId).orElseThrow(()-> new EntityNotFoundException("Ingresso não encontrado")));
    }
}
