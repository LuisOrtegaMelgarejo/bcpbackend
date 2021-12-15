package com.bcp.exchangeRate.application.ports.out;

import com.bcp.exchangeRate.application.domains.entities.ExchangeRate;
import com.bcp.exchangeRate.application.domains.requests.ExchangeRateRequest;
import com.bcp.exchangeRate.application.domains.responses.GenericResponse;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

public interface ExchangeRateRepositoryPort {
    public Single<ExchangeRate> loadExchangeRate(String originCurrency, String targetCurrency);
    public Single<ExchangeRate> loadExchangeRateById(Long id);
    public Observable<ExchangeRate> listExchangeRates(Long id, String originCurrency, String targetCurrency);
    public Single<GenericResponse> updateExchangeRate(ExchangeRate exchangeRate);
    public Single<GenericResponse> saveExchangeRate(ExchangeRateRequest exchangeRate);
}
