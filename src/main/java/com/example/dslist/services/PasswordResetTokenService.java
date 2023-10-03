package com.example.dslist.services;

import com.example.dslist.dto.UserDTO;
import com.example.dslist.entities.PasswordResetToken;
import com.example.dslist.entities.User;
import com.example.dslist.repositories.PasswordResetTokenRepository;
import com.example.dslist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class PasswordResetTokenService implements com.example.dslist.services.interfaces.PasswordResetTokenService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository repository;

    @Autowired
    private PasswordEncoder encoder;
    public void saveResetPasswordToken(User user, String token){

        PasswordResetToken newToken = new PasswordResetToken(user, token);
        repository.save(newToken);

    }

    public String validateToken(String token){
        Optional<PasswordResetToken> passwordResetToken = repository.findByToken(token);

        Calendar calendar = Calendar.getInstance();

        if(passwordResetToken.isEmpty()){
            return "INVALID";
        } else if(passwordResetToken.get().getExpirationTime().getTime() - calendar.getTime().getTime() <= 0){
            return "EXPIRED";
        }

        return "VALID";
    }

    public Optional<User> findUserByToken(String token) {
        return Optional.ofNullable(repository.findByToken(token).get().getUser());
    }


    public void resetPassword(User user, String newPassword) {
        Optional<User> userToBeFound = userRepository.findByEmail(user.getEmail());

        if(userToBeFound.isPresent()){
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
        }
    }
}