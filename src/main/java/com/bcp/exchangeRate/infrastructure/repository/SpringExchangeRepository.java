package com.bcp.exchangeRate.infrastructure.repository;

import com.bcp.exchangeRate.application.domains.entities.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringExchangeRepository extends JpaRepository<ExchangeRate,Long> {
    Optional<ExchangeRate> findByOriginCurrencyAndTargetCurrency(String originCurrency, String targetCurrency);
}

