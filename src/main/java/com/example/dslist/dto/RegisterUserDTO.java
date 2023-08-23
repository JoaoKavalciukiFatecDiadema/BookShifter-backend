package com.example.dslist.dto;

import com.example.dslist.entities.Role;
import lombok.Data;

import java.util.List;

@Data
public class RegisterUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Role> roles;
    
}

