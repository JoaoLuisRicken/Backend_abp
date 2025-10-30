package com.backend.jjj.cinema_api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.backend.jjj.cinema_api.models.UsersModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {
    @Value("${api.jwt.secretKey.token}")
    private String secretKey;

    public String  generateToken(UsersModel account){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create().withIssuer("api-kambam").withSubject(account.getEmail()).withExpiresAt(genExpiration()).sign(algorithm);
            return token;
        }catch(JWTCreationException ex){
            throw new RuntimeException("Erro ao gerar o token",ex);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm).withIssuer("api-kambam").build().verify(token).getSubject();
        }catch(JWTVerificationException ex){
            return "";
        }
    }
    private Instant genExpiration(){
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
    }
}