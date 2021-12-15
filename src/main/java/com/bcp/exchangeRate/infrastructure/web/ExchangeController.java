package com.bcp.exchangeRate.infrastructure.web;

import com.bcp.exchangeRate.application.domains.requests.ExchangeRequest;
import com.bcp.exchangeRate.application.domains.requests.UpdateRequest;
import com.bcp.exchangeRate.application.domains.responses.ExchangeResponse;
import com.bcp.exchangeRate.application.domains.responses.UpdateResponse;
import com.bcp.exchangeRate.application.ports.in.ExchangeUseCase;
import com.bcp.exchangeRate.application.ports.in.UpdateRateUseCase;
import io.reactivex.Single;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeUseCase exchangeUseCase;
    private final UpdateRateUseCase updateRateUseCase;

    public ExchangeController(ExchangeUseCase exchangeUseCase,
                              UpdateRateUseCase updateRateUseCase){
        this.exchangeUseCase = exchangeUseCase;
        this.updateRateUseCase = updateRateUseCase;
    }

    @PostMapping("/calculate")
    public Single<ExchangeResponse> calculateExchangeOperation(@RequestBody ExchangeRequest request) {
        return exchangeUseCase.calculate(request);
    }

    @PostMapping("/{id}")
    public Single<UpdateResponse> updateExchangeRate(@PathVariable final Long id, @RequestBody UpdateRequest request) {
        return updateRateUseCase.updateExchangeRate(id,request);
    }
}
