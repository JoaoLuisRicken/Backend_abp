package com.backend.jjj.cinema_api.services;

import com.backend.jjj.cinema_api.dto.rooms.RequestRoom;
import com.backend.jjj.cinema_api.dto.rooms.ResponseRoom;
import com.backend.jjj.cinema_api.mapper.RoomsMapper;
import com.backend.jjj.cinema_api.models.RoomsModel;
import com.backend.jjj.cinema_api.repository.RoomsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomsService {

    private final RoomsRepository roomsRepository;
    private final RoomsMapper roomsMapper;

    public ResponseRoom addRoom(RequestRoom request) {
        return roomsMapper.toResponse(roomsRepository.save(roomsMapper.toEntity(request)));
    }

    public ResponseRoom updateRoom(String idRoom,RequestRoom request) {
        RoomsModel room = getRoomById(idRoom);
        roomsMapper.updateRoom(request,room);
        return roomsMapper.toResponse(roomsRepository.save(room));
    }

    public List<ResponseRoom> getAllRooms() {
        List<RoomsModel> rooms = roomsRepository.findAll();
        return rooms.stream().map(roomsMapper::toResponse).toList();
    }

    public ResponseRoom getRoom(String roomId) {
        return roomsMapper.toResponse(getRoomById(roomId));
    }

    private RoomsModel getRoomById(String id) {
        return roomsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrada a sala"));
    }
}
