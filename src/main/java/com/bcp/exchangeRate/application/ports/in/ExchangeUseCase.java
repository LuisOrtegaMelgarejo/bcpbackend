package com.bcp.exchangeRate.application.ports.in;

import com.bcp.exchangeRate.application.domains.requests.ExchangeRequest;
import com.bcp.exchangeRate.application.domains.responses.ExchangeResponse;
import io.reactivex.Single;

public interface ExchangeUseCase {
    Single<ExchangeResponse> calculate(ExchangeRequest exchangeRequest);
}
