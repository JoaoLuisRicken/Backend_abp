package com.backend.jjj.cinema_api.services;

import com.backend.jjj.cinema_api.models.UsersModel;
import com.backend.jjj.cinema_api.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {
    private final UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username);
    }

    public static UsersModel getAccount(){
        var auth =  SecurityContextHolder.getContext().getAuthentication();
        return (UsersModel) auth.getPrincipal();
    }

}
