package com.oauth2.authorization_server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegisterDTO {

    private String clientId;
    private String clientSecret;
    private Set<ClientAuthenticationMethod> authenticationMethods;
    private Set<AuthorizationGrantType> authorizationGrantTypes;
    private Set<String> redirectUris;
    private Set<String> scopes;
    private boolean requireProofKey;
}
