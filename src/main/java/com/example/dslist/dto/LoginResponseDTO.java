package com.example.dslist.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    String token;

    public LoginResponseDTO(){

    }

    public LoginResponseDTO(String token){
        this.token = token;
    }
}
