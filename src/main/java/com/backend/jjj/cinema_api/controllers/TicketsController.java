package com.backend.jjj.cinema_api.controllers;

import com.backend.jjj.cinema_api.dto.tickets.RequestTicket;
import com.backend.jjj.cinema_api.dto.tickets.ResponseTicket;
import com.backend.jjj.cinema_api.services.TicketsService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
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
        return ticketsService.getTicket(ticketId);
    }

    @GetMapping(value = "/online-session/{ticketId}", produces = "video/mp4")
    public void viewMovie(@PathVariable("ticketId") String ticketId, HttpServletResponse response) {
        try (InputStream videoStream = ticketsService.streamSession(ticketId)) {
            response.setContentType("video/mp4");
            videoStream.transferTo(response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao transmitir o vídeo", e);
        }
    }

}
