package com.bcp.exchangeRate.infrastructure.repository;

import com.bcp.exchangeRate.application.domains.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringLogRepository extends JpaRepository<Log,Long> {
}

