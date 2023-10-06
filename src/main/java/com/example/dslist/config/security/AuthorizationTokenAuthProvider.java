package com.example.dslist.config.security;

import com.example.dslist.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class AuthorizationTokenAuthProvider implements AuthenticationProvider {




    @Override
    @Bean("UsernamePasswordToken")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //Metodo ta bugado, preciso dewscobrir como salvar as credenciais que o usuario vai informar, salvar elas (depois deletar)
        //e usar aqui para gerar o token de login
        if((authentication instanceof AnonymousAuthenticationToken)){
            return new UsernamePasswordAuthenticationToken(username, password,
                    Arrays.asList("ROLE_USER").stream().map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList()));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
