package com.bcp.exchangeRate.application.ports.in;

import com.bcp.exchangeRate.application.domains.requests.ExchangeRateRequest;
import com.bcp.exchangeRate.application.domains.responses.GenericResponse;
import io.reactivex.Single;

public interface NewExchangeRateUseCase {
    Single<GenericResponse> addExchangeRate(ExchangeRateRequest request);
}
