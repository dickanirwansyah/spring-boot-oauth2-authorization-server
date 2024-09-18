package com.oauth2.authorization_server.repository;

import com.oauth2.authorization_server.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {

    Optional<Roles> findByRoleName(String roleName);
}
