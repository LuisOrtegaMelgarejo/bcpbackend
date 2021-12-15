package com.bcp.exchangeRate.application.ports.in;

import com.bcp.exchangeRate.application.domains.requests.UpdateRequest;
import com.bcp.exchangeRate.application.domains.responses.GenericResponse;
import io.reactivex.Single;

public interface UpdateRateUseCase {
    Single<GenericResponse> updateExchangeRate(Long id, UpdateRequest request);
}
