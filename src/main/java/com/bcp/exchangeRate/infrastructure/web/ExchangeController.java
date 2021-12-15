package com.bcp.exchangeRate.infrastructure.web;

import com.bcp.exchangeRate.application.domains.entities.ExchangeRate;
import com.bcp.exchangeRate.application.domains.requests.ExchangeRequest;
import com.bcp.exchangeRate.application.domains.requests.ExchangeRateRequest;
import com.bcp.exchangeRate.application.domains.requests.UpdateRequest;
import com.bcp.exchangeRate.application.domains.responses.ExchangeResponse;
import com.bcp.exchangeRate.application.domains.responses.GenericResponse;
import com.bcp.exchangeRate.application.ports.in.ExchangeUseCase;
import com.bcp.exchangeRate.application.ports.in.ListExchangeRateUseCase;
import com.bcp.exchangeRate.application.ports.in.NewExchangeRateUseCase;
import com.bcp.exchangeRate.application.ports.in.UpdateRateUseCase;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeUseCase exchangeUseCase;
    private final UpdateRateUseCase updateRateUseCase;
    private final NewExchangeRateUseCase newExchangeRateUseCase;
    private final ListExchangeRateUseCase listExchangeRateUseCase;

    public ExchangeController(ExchangeUseCase exchangeUseCase,
                              UpdateRateUseCase updateRateUseCase,
                              NewExchangeRateUseCase newExchangeRateUseCase,
                              ListExchangeRateUseCase listExchangeRateUseCase){
        this.exchangeUseCase = exchangeUseCase;
        this.updateRateUseCase = updateRateUseCase;
        this.newExchangeRateUseCase = newExchangeRateUseCase;
        this.listExchangeRateUseCase = listExchangeRateUseCase;
    }

    @PostMapping("/calculate")
    public Single<ExchangeResponse> calculateExchangeOperation(@RequestBody ExchangeRequest request) {
        return exchangeUseCase.calculate(request);
    }

    @GetMapping("/")
    public Observable<ExchangeRate> getExchangeRate(@Param("id") Long id,
                                                    @Param("originCurrency") String originCurrency,
                                                    @Param("targetCurrency") String targetCurrency) {
        return listExchangeRateUseCase.listExchangeRate(id,originCurrency,targetCurrency);
    }

    @PostMapping("/")
    public Single<GenericResponse> addExchangeRate(@RequestBody ExchangeRateRequest request) {
        return newExchangeRateUseCase.addExchangeRate(request);
    }

    @PutMapping("/{id}")
    public Single<GenericResponse> updateExchangeRate(@PathVariable final Long id, @RequestBody UpdateRequest request) {
        return updateRateUseCase.updateExchangeRate(id,request);
    }
}
