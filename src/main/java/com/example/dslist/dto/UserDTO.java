package com.example.dslist.dto;

import com.example.dslist.entities.Role;
import com.example.dslist.entities.User;

import java.util.List;

public class UserDTO {
    public String firstName;
    public String lastName;
    public String email;
    public List<Role> roles;

    public UserDTO(){

    }

    public UserDTO(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.roles = user.getRoles().stream().toList();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
