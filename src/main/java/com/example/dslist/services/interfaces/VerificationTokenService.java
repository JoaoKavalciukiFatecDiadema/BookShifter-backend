package com.example.dslist.services.interfaces;

import com.example.dslist.entities.User;
import com.example.dslist.entities.VerificationToken;

import java.util.Optional;

public interface VerificationTokenService {
    String validateToken(String token);

    void saveVerificationToken(User user, String token);

    Optional<VerificationToken> findByToken(String token);
}
