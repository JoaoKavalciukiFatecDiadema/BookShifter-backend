package com.example.dslist.services.interfaces;

import com.example.dslist.dto.UserDTO;
import com.example.dslist.entities.User;

import java.time.Instant;

public interface JwtTokenService {
    String generateToken(User user);

    String validateToken(String token);

}
