package com.example.SpringRestProject.rest;

import lombok.Data;

@Data
public class AuthenticateRequestDTO {
    private String email;
    private String password;
}
