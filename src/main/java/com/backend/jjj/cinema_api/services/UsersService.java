package com.backend.jjj.cinema_api.services;

import com.backend.jjj.cinema_api.dto.users.LoginDto;
import com.backend.jjj.cinema_api.dto.users.RequestUser;
import com.backend.jjj.cinema_api.dto.users.RequestUserUpdate;
import com.backend.jjj.cinema_api.dto.users.ResponseUser;
import com.backend.jjj.cinema_api.enums.UserRole;
import com.backend.jjj.cinema_api.infra.security.TokenService;
import com.backend.jjj.cinema_api.mapper.UsersMapper;
import com.backend.jjj.cinema_api.models.UsersModel;
import com.backend.jjj.cinema_api.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public String login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.username(),loginDto.password());
        Authentication authentication = authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((UsersModel) authentication.getPrincipal());
    }

    public ResponseUser addClient(RequestUser request) {
        UsersModel user = usersMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.CLIENT);
        return usersMapper.toResponse(usersRepository.save(user));
    }

    public ResponseUser addEmployee(RequestUser request) {
        UsersModel user = usersMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.EMPLOYEE);
        return usersMapper.toResponse(usersRepository.save(user));
    }

    public ResponseUser updateUser(String userId, RequestUserUpdate request) {
        UsersModel user = getUserById(userId);
        usersMapper.updateUser(request, user);
        return usersMapper.toResponse(usersRepository.save(user));
    }

    public void deleteUser(String userId) {
        UsersModel user = getUserById(userId);
        usersRepository.delete(user);
    }

    private UsersModel getUserById(String id){
       return usersRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Não foi encontrado o usuario"));
    }

    private ResponseUser getUser(String userId) {
        return usersMapper.toResponse(getUserById(userId));
    }
}
