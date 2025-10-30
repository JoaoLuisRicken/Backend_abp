package com.backend.jjj.cinema_api.controllers;

import com.backend.jjj.cinema_api.dto.users.LoginDto;
import com.backend.jjj.cinema_api.dto.users.RequestUser;
import com.backend.jjj.cinema_api.dto.users.RequestUserUpdate;
import com.backend.jjj.cinema_api.dto.users.ResponseUser;
import com.backend.jjj.cinema_api.services.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody @Valid LoginDto login){
        return usersService.login(login);
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUser addAdmin(@RequestBody @Valid RequestUser request){
        return usersService.addAdmin(request);
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUser addEmployee(@RequestBody @Valid RequestUser request){
        return usersService.addEmployee(request);
    }

    @PostMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUser addClient(@RequestBody @Valid RequestUser request){
        return usersService.addClient(request);
    }

    @GetMapping("/account")
    @ResponseStatus(HttpStatus.OK)
    public ResponseUser getAccount(){
        return usersService.getUser();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseUser updateAccount(@RequestBody @Valid RequestUserUpdate request){
        return usersService.updateUser(request);
    }

}
