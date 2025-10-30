package com.backend.jjj.cinema_api.controllers;

import com.backend.jjj.cinema_api.dto.seats.RequestSeats;
import com.backend.jjj.cinema_api.dto.seats.ResponseSeats;
import com.backend.jjj.cinema_api.services.SeatsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/seats")
@RequiredArgsConstructor
public class SeatsController {
    private final SeatsService seatsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseSeats addSeats(@RequestBody @Valid RequestSeats requestSeats){
        return seatsService.addSeats(requestSeats);
    }

    @GetMapping("/room/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseSeats> getSeats(@PathVariable String roomId){
        return seatsService.getAllSeats(roomId);
    }

    @GetMapping("/{seatId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSeats getSeat(@PathVariable String seatId){
        return seatsService.getSeat(seatId);
    }

    @PatchMapping("/{seatId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSeats updateSeat(@PathVariable String seatId, @RequestBody RequestSeats request){
        return seatsService.updateSeats(seatId,request);
    }

    @PatchMapping("/disable/{seatId}")
    @ResponseStatus(HttpStatus.OK)
    public void disableSeat(@PathVariable String seatId){
        seatsService.seatDisable(seatId);
    }
}
