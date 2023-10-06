package com.example.dslist.controllers.rest;

import com.example.dslist.dto.PasswordResetDTO;
import com.example.dslist.entities.User;
import com.example.dslist.events.RestPasswordRecoveryEvent;
import com.example.dslist.services.PasswordResetTokenService;
import com.example.dslist.services.UserService;
import com.example.dslist.utils.UrlUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/rest-forgot-password")
public class RestPasswordResetController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PasswordResetTokenService service;

    @PostMapping
    public ResponseEntity sendResetPasswordRequest(@RequestBody PasswordResetDTO dto, HttpServletRequest request){
        String email = dto.getEmail();
        Optional<User> user = userService.findByEmail(email);

        if(user.isEmpty()){
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        publisher.publishEvent(new RestPasswordRecoveryEvent(user.get(), UrlUtil.getApplicationUrl(request)));
        return  ResponseEntity.status(200).body("Email de mudança de senha enviado");
    }

    @PostMapping("/password-reset")
    public ResponseEntity resetPassword(@RequestBody PasswordResetDTO request, @RequestParam("token") String requestToken){
        String validationTokenResult = service.validateToken(requestToken);
        String newPassword = request.getNewPassword();
        if(validationTokenResult.equalsIgnoreCase("valid")){
            Optional<User> user = service.findUserByToken(requestToken);

            if(user.isPresent()){
                if (checkValidation(newPassword, request.getNewPasswordConfirmation()).equalsIgnoreCase("valid")){
                    service.resetPassword(user.get(), newPassword);
                    return ResponseEntity.status(200).body("Senha alterada com sucesso");
                } else ResponseEntity.status(409).body("AS senhas não coincidem");
            }
        }

        return ResponseEntity.status(406).body("Token não pertence a nenhum usuário ou expirou");
    }

    private String checkValidation(String newPassword, String newPasswordConfirmation){
        if(newPassword.equalsIgnoreCase(newPasswordConfirmation)){
            return "VALID";
        }
        return "INVALID";
    }
}
