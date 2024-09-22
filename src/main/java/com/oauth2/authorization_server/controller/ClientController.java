package com.oauth2.authorization_server.controller;

import com.oauth2.authorization_server.dto.ClientRegisterDTO;
import com.oauth2.authorization_server.dto.RestApiData;
import com.oauth2.authorization_server.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping(value = "/create")
    public ResponseEntity<RestApiData> create(@RequestBody ClientRegisterDTO clientRegisterDTO){
        return ResponseEntity.ok(new RestApiData("success",
                clientService.create(clientRegisterDTO)));
    }
}
