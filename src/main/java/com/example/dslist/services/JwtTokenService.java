package com.example.dslist.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.dslist.dto.UserDTO;
import com.example.dslist.entities.User;
import com.example.dslist.utils.TokensExpirationTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService implements com.example.dslist.services.interfaces.JwtTokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    private String issuer = "BookShifter";
    @Override
    public String generateToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer(issuer).withSubject(user.getEmail())
                    .withExpiresAt(TokensExpirationTime.getExpirantionTime())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token", exception);
        }
    }

    @Override
    public String validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer(issuer)
                    .build().verify(token).getSubject();
        } catch (JWTVerificationException exception){
            //Este retorno significa que não há token.
            return "";
        }
    }
}
