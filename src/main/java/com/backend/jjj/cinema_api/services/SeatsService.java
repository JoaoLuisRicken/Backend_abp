package com.backend.jjj.cinema_api.services;

import com.backend.jjj.cinema_api.dto.seats.RequestSeats;
import com.backend.jjj.cinema_api.dto.seats.ResponseSeats;
import com.backend.jjj.cinema_api.mapper.SeatsMapper;
import com.backend.jjj.cinema_api.models.SeatsModel;
import com.backend.jjj.cinema_api.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatsService {
    private final SeatsRepository seatsRepository;
    private final SeatsMapper seatsMapper;

    public ResponseSeats addSeats(RequestSeats request){
        return seatsMapper.toDto(seatsRepository.save(seatsMapper.toEntity(request)));
    }

    public ResponseSeats getSeat(String seatsId){
        return seatsMapper.toDto(getSeatsModel(seatsId));
    }

    public List<ResponseSeats> getAllSeats(String roomId){
        return seatsRepository.findAllByRoomRoomId(roomId).stream().map(seatsMapper::toDto).toList();
    }

    public ResponseSeats updateSeats(String seatsId,RequestSeats request){
        SeatsModel seat = getSeatsModel(seatsId);
        seatsMapper.updateSeat(request,seat);
        return seatsMapper.toDto(seatsRepository.save(seat));
    }

    public void seatDisable(String seatsId){
        SeatsModel seat = getSeatsModel(seatsId);
        seat.setActive(false);
        seatsRepository.save(seat);
    }

    private SeatsModel getSeatsModel(String seatsId){
        return seatsRepository.findById(seatsId).orElseThrow(()-> new RuntimeException("Acento não encontrado"));
    }
}
