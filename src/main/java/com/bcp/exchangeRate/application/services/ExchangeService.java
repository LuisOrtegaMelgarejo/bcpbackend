package com.bcp.exchangeRate.application.services;

import com.bcp.exchangeRate.application.domains.requests.UpdateRequest;
import com.bcp.exchangeRate.application.domains.responses.ExchangeResponse;
import com.bcp.exchangeRate.application.domains.responses.UpdateResponse;
import com.bcp.exchangeRate.application.ports.in.UpdateRateUseCase;
import com.bcp.exchangeRate.application.ports.out.ExchangeRateRepositoryPort;
import com.bcp.exchangeRate.application.domains.requests.ExchangeRequest;
import com.bcp.exchangeRate.application.ports.in.ExchangeUseCase;
import io.reactivex.Single;

public class ExchangeService implements ExchangeUseCase, UpdateRateUseCase {
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
    public Single<UpdateResponse> updateExchangeRate(Long id, UpdateRequest request) {
        return exchangeRateRepositoryPort
                .loadExchangeRateById(id)
                .map(exchangeRate -> {
                    exchangeRate.setRate(request.getNewRate());
                    return exchangeRateRepositoryPort
                            .updateExchangeRate(exchangeRate)
                            .blockingGet();
                });
    }
}
