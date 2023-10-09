package com.example.dslist.controllers.rest;


import com.example.dslist.dto.LoginDTO;
import com.example.dslist.dto.LoginResponseDTO;
import com.example.dslist.entities.User;
import com.example.dslist.services.interfaces.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest-login")
public class RestLoginController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtTokenService jwtTokenService;
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO credentials){
        var usernamePassword= new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password());
        var auth = this.manager.authenticate(usernamePassword);

        var jwtToken =jwtTokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(jwtToken));
    }

}
