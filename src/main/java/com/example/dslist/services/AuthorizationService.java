package com.example.dslist.services;

import com.example.dslist.config.security.DsListUsersDetails;
import com.example.dslist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DsListUserDetailsService implements UserDetailsService {
    @Autowired
    private  UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).map(DsListUsersDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
    }
}
