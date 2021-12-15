package com.bcp.exchangeRate.application.services;

import com.bcp.exchangeRate.application.domains.entities.ExchangeRate;
import com.bcp.exchangeRate.application.domains.requests.ExchangeRateRequest;
import com.bcp.exchangeRate.application.domains.requests.UpdateRequest;
import com.bcp.exchangeRate.application.domains.responses.ExchangeResponse;
import com.bcp.exchangeRate.application.domains.responses.GenericResponse;
import com.bcp.exchangeRate.application.ports.in.ListExchangeRateUseCase;
import com.bcp.exchangeRate.application.ports.in.NewExchangeRateUseCase;
import com.bcp.exchangeRate.application.ports.in.UpdateRateUseCase;
import com.bcp.exchangeRate.application.ports.out.ExchangeRateRepositoryPort;
import com.bcp.exchangeRate.application.domains.requests.ExchangeRequest;
import com.bcp.exchangeRate.application.ports.in.ExchangeUseCase;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

public class ExchangeService implements ExchangeUseCase, UpdateRateUseCase, NewExchangeRateUseCase, ListExchangeRateUseCase {
    private ExchangeRateRepositoryPort exchangeRateRepositoryPort;

    public ExchangeService(ExchangeRateRepositoryPort exchangeRateRepositoryPort) {
        this.exchangeRateRepositoryPort = exchangeRateRepositoryPort;
    }
    @Override
    public Single<ExchangeResponse> calculate(ExchangeRequest exchangeRequest) {
        return exchangeRateRepositoryPort
                .loadExchangeRate(exchangeRequest.getOriginCurrency(),exchangeRequest.getTargetCurrency())
                .map(exchangeRate -> exchangeRate.calculateExchange(exchangeRequest.getAmount()));
    }

    @Override
    public Single<GenericResponse> updateExchangeRate(Long id, UpdateRequest request) {
        return exchangeRateRepositoryPort
                .loadExchangeRateById(id)
                .map(exchangeRate -> {
                    exchangeRate.setRate(request.getNewRate());
                    return exchangeRateRepositoryPort
                            .updateExchangeRate(exchangeRate)
                            .blockingGet();
                });
    }

    @Override
    public Single<GenericResponse> addExchangeRate(ExchangeRateRequest request) {
        return exchangeRateRepositoryPort.saveExchangeRate(request);
    }

    @Override
    public Observable<ExchangeRate> listExchangeRate(Long id, String originCurrency,String targetCurrency) {
        return exchangeRateRepositoryPort.listExchangeRates(id,originCurrency,targetCurrency);
    }
}
