package com.oauth2.authorization_server.service;

import com.oauth2.authorization_server.dto.ClientRegisterDTO;
import com.oauth2.authorization_server.dto.MessageDTO;
import com.oauth2.authorization_server.entity.Client;
import com.oauth2.authorization_server.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    private Client clientFromDTO(ClientRegisterDTO clientRegisterDTO){
        Client client = Client.builder()
                .clientId(clientRegisterDTO.getClientId())
                .clientSecret(passwordEncoder.encode(clientRegisterDTO.getClientSecret()))
                .authenticationMethods(clientRegisterDTO.getAuthenticationMethods())
                .authorizationGrantTypes(clientRegisterDTO.getAuthorizationGrantTypes())
                .redirectUris(clientRegisterDTO.getRedirectUris())
                .scopes(clientRegisterDTO.getScopes())
                .requireProofKey(clientRegisterDTO.isRequireProofKey())
                .build();
        return client;
    }

    public MessageDTO create(ClientRegisterDTO clientRegisterDTO){
        Client client = clientFromDTO(clientRegisterDTO);
        clientRepository.save(client);
        return new MessageDTO("client "+client.getClientId()+" is successfully created !");
    }


    @Override
    public RegisteredClient findById(String id) {
        Client client = clientRepository.findByClientId(id)
                .orElseThrow(() -> new RuntimeException("register client id not found !"));
        return Client.toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(()-> new RuntimeException("client not found"));
        return Client.toRegisteredClient(client);
    }
}
