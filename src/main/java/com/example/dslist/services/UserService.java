package com.example.dslist.services;

import com.example.dslist.dto.RegisterUserDTO;

import com.example.dslist.dto.UserDTO;
import com.example.dslist.entities.Role;
import com.example.dslist.entities.User;
import com.example.dslist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements com.example.dslist.services.interfaces.UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<UserDTO> findAll(){
        var result = repository.findAll();

        List<UserDTO> users = result.stream().map(UserDTO::new).toList();
        return users;
    }

    @Override
    public User registerUser(RegisterUserDTO dto){
      User newUser = new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
              passwordEncoder.encode(dto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
      return repository.save(newUser);
    }
    @Override
    public Optional<User> findByEmail(String email){
        return Optional.ofNullable(repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")));
    }
}
