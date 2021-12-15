package com.bcp.exchangeRate.application.ports.in;

import com.bcp.exchangeRate.application.domains.requests.UpdateRequest;
import com.bcp.exchangeRate.application.domains.responses.UpdateResponse;
import io.reactivex.Single;

public interface UpdateRateUseCase {
    Single<UpdateResponse> updateExchangeRate(Long id, UpdateRequest request);
}
