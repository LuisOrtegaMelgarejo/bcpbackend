package com.bcp.exchangeRate.infrastructure.repository;

import com.bcp.exchangeRate.application.domains.entities.UserBcp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringUserRepository extends JpaRepository<UserBcp,Long> {
    Optional<UserBcp> findByUsername (String username);
}

