package com.backend.jjj.cinema_api.controllers;

import com.backend.jjj.cinema_api.dto.tickets.RequestTicket;
import com.backend.jjj.cinema_api.dto.tickets.ResponseTicket;
import com.backend.jjj.cinema_api.services.TicketsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tickets")
@RequiredArgsConstructor
public class TicketsController {
    private final TicketsService ticketsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseTicket createTicket(@RequestBody @Valid RequestTicket ticket){
        return ticketsService.addTicket(ticket);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseTicket> getTickets(){
        return ticketsService.getTickets();
    }

    @GetMapping("/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseTicket getTicketById(@PathVariable("ticketId") String ticketId){
        return ticketsService.getTicketById(ticketId);
    }
}
