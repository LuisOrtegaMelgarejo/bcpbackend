package com.bcp.exchangeRate.adapters.persistance;

import com.bcp.exchangeRate.application.domains.entities.ExchangeRate;
import com.bcp.exchangeRate.application.domains.responses.UpdateResponse;
import com.bcp.exchangeRate.application.ports.out.ExchangeRateRepositoryPort;
import com.bcp.exchangeRate.infrastructure.repository.SpringExchangeRepository;
import io.reactivex.Single;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class ExchangeRateRepository implements ExchangeRateRepositoryPort {

    private SpringExchangeRepository repository;

    public ExchangeRateRepository(SpringExchangeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<ExchangeRate> loadExchangeRate(String originCurrency, String targetCurrency) {
        return Single.create(singleEmitter -> {
             Optional<ExchangeRate> exchangeRate = repository.findByOriginCurrencyAndTargetCurrency(originCurrency,targetCurrency);
             exchangeRate.ifPresentOrElse(singleEmitter::onSuccess,() -> {
                 singleEmitter.onError(new ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange Rate not found"));
             });
        });
    }

    @Override
    public Single<ExchangeRate> loadExchangeRateById(Long id) {
        return Single.create( singleEmitter -> {
            Optional<ExchangeRate> exchangeRate = repository.findById(id);
            exchangeRate.ifPresentOrElse(singleEmitter::onSuccess,() -> {
                singleEmitter.onError(new ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange Rate not found"));
            });
        });
    }

    @Override
    public Single<UpdateResponse> updateExchangeRate(ExchangeRate exchangeRate) {
        return Single.create( singleEmitter -> {
            try {
                repository.save(exchangeRate);
                singleEmitter.onSuccess(new UpdateResponse("SUCCESS"));
            }catch (Exception ex){
                singleEmitter.onError(new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Failed at update exchange rate"));
            }
        });
    }
}
