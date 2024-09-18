package com.oauth2.authorization_server.dto;


import java.util.List;

public record RegisterAccountDTO(
     String username,
     String password,
     List<String> roles
){}
