package com.oauth2.authorization_server.service;

import com.oauth2.authorization_server.dto.MessageDTO;
import com.oauth2.authorization_server.dto.RegisterAccountDTO;
import com.oauth2.authorization_server.entity.Account;
import com.oauth2.authorization_server.entity.Roles;
import com.oauth2.authorization_server.enums.RolesName;
import com.oauth2.authorization_server.repository.AccountRepository;
import com.oauth2.authorization_server.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    public MessageDTO createAccount(RegisterAccountDTO registerAccountDTO){
        Account account = Account.builder()
                .username(registerAccountDTO.username())
                .password(passwordEncoder.encode(registerAccountDTO.password()))
                .build();
        Set<Roles> setRoles = new HashSet<>();
        registerAccountDTO.roles().forEach(role -> {
            //sementara find by roles by enum
            Roles rolesUser = rolesRepository.findByRoleName(RolesName.ROLE_USER.name())
                    .orElseThrow(() -> new RuntimeException("roles user not found"));

            Roles rolesAdmin = rolesRepository.findByRoleName(RolesName.ROLE_ADMIN.name())
                    .orElseThrow(() -> new RuntimeException("roles admin not found"));

            setRoles.add(rolesUser);
            setRoles.add(rolesAdmin);
        });
        account.setRoles(setRoles);
        accountRepository.save(account);
        return new MessageDTO("user "+account.getUsername()+" saved !");
    }
}
