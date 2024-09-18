package com.oauth2.authorization_server.controller;

import com.oauth2.authorization_server.dto.RegisterAccountDTO;
import com.oauth2.authorization_server.dto.RestApiData;
import com.oauth2.authorization_server.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AccountController {

    private final AccountService accountService;

    @PostMapping(value = "/create-account")
    public ResponseEntity<RestApiData> createAccount(@RequestBody RegisterAccountDTO registerAccountDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new RestApiData("success", accountService.createAccount(registerAccountDTO)));
    }
}
