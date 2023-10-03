package com.example.dslist.services.interfaces;

import com.example.dslist.dto.RegisterUserDTO;
import com.example.dslist.dto.UserDTO;
import com.example.dslist.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAll();

    User registerUser(RegisterUserDTO dto);

    Optional<User> findByEmail(String email);
}
