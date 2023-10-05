package com.example.dslist.controllers.rest;

import com.example.dslist.dto.RegisterUserDTO;
import com.example.dslist.entities.User;
import com.example.dslist.entities.VerificationToken;
import com.example.dslist.events.RegistrationCompleteEvent;
import com.example.dslist.services.UserService;
import com.example.dslist.services.VerificationTokenService;
import com.example.dslist.utils.UrlUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest-register")
public class RestRegisterController {
    @Autowired
    private UserService service;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private VerificationTokenService tokenService;

    @PostMapping
    public ResponseEntity registerUser(@RequestBody RegisterUserDTO registerDTO, final HttpServletRequest request){
        User user = service.registerUser(registerDTO);
        publisher.publishEvent(new RegistrationCompleteEvent(user, UrlUtil.getApplicationUrl(request)));
        return ResponseEntity.ok("Email enviado com sucesso");
    }

    //Implementar rest event listener
    @GetMapping("/rest-enableAccount")
    public ResponseEntity enableAccount(@RequestParam("token") String token){
        Optional<VerificationToken> verificationToken = tokenService.findByToken(token);
        if(verificationToken.isPresent() && verificationToken.get().getUser().isEnabled()){
            return ResponseEntity.ok("Conta ativada com sucesso, por favor façva seu login");
        }

        String validationResult = tokenService.validateToken(token);

        return switch (validationResult.toLowerCase()) {
            case "expired" -> ResponseEntity.status(426).body("Token expirado, por favor gere outro token");
            case "valid" -> ResponseEntity.status(202).body("Token válido, por favor faça seu login");
            default -> ResponseEntity.status(400).body("Token inválido, por favor gere um token válido");
        };
    }
}
