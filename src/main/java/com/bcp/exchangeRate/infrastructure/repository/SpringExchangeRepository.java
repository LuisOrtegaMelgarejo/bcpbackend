package com.bcp.exchangeRate.infrastructure.repository;

import com.bcp.exchangeRate.application.domains.entities.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringExchangeRepository extends JpaRepository<ExchangeRate,Long> {
    Optional<ExchangeRate> findByOriginCurrencyAndTargetCurrency(String originCurrency, String targetCurrency);
    List<ExchangeRate> findByOriginCurrency(String originCurrency);
    List<ExchangeRate> findByTargetCurrency(String targetCurrency);
}

