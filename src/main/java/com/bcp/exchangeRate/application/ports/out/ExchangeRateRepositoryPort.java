package com.bcp.exchangeRate.application.ports.out;

import com.bcp.exchangeRate.application.domains.entities.ExchangeRate;
import com.bcp.exchangeRate.application.domains.responses.UpdateResponse;
import io.reactivex.Single;

public interface ExchangeRateRepositoryPort {
    public Single<ExchangeRate> loadExchangeRate(String originCurrency, String targetCurrency);
    public Single<ExchangeRate> loadExchangeRateById(Long id);
    public Single<UpdateResponse> updateExchangeRate(ExchangeRate exchangeRate);
}
