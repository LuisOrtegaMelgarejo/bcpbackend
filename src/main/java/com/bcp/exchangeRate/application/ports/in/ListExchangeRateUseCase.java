package com.bcp.exchangeRate.application.ports.in;

import com.bcp.exchangeRate.application.domains.entities.ExchangeRate;
import io.reactivex.Observable;

import java.util.List;

public interface ListExchangeRateUseCase {
    public Observable<ExchangeRate> listExchangeRate(Long id, String originalCurrency, String targetCurrency);
}
