package com.backend.jjj.cinema_api.controllers;

import com.backend.jjj.cinema_api.dto.rooms.RequestRoom;
import com.backend.jjj.cinema_api.dto.rooms.ResponseRoom;
import com.backend.jjj.cinema_api.services.RoomsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rooms")
@RequiredArgsConstructor
public class RoomsController {
    private final RoomsService roomsService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseRoom createRoom(@RequestBody @Valid  RequestRoom request){
        return roomsService.addRoom(request);
    }

    @PatchMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseRoom updateRoom(@PathVariable("roomId") String roomId, @RequestBody   RequestRoom request){
        return roomsService.updateRoom(roomId, request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseRoom> getRooms(){
        return roomsService.getAllRooms();
    }

    @GetMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseRoom getRoom(@PathVariable("roomId") String roomId){
        return roomsService.getRoom(roomId);
    }
}
