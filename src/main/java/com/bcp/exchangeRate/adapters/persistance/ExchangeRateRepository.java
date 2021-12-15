package com.bcp.exchangeRate.adapters.persistance;

import com.bcp.exchangeRate.application.domains.entities.ExchangeRate;
import com.bcp.exchangeRate.application.domains.requests.ExchangeRateRequest;
import com.bcp.exchangeRate.application.domains.responses.GenericResponse;
import com.bcp.exchangeRate.application.ports.out.ExchangeRateRepositoryPort;
import com.bcp.exchangeRate.infrastructure.repository.SpringExchangeRepository;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
    public Observable<ExchangeRate> listExchangeRates(Long id, String originCurrency, String targetCurrency) {
        List<ExchangeRate> listExchangeRates;
        Optional<ExchangeRate> e;
        if (id != null || (originCurrency != null && targetCurrency != null)) {
            listExchangeRates = new ArrayList<>();
            if (id != null)  e = this.repository.findById(id); else e = this.repository.findByOriginCurrencyAndTargetCurrency(originCurrency,targetCurrency);
            if(e.isPresent()) listExchangeRates.add(e.get()); else throw new NoSuchElementException();
        }else if (originCurrency!=null){
            listExchangeRates = this.repository.findByOriginCurrency(originCurrency);
        }else {
            listExchangeRates = this.repository.findByTargetCurrency(targetCurrency);
        }
        return Observable.fromIterable(listExchangeRates);
    }

    @Override
    public Single<GenericResponse> updateExchangeRate(ExchangeRate exchangeRate) {
        return Single.create( singleEmitter -> {
            try {
                repository.save(exchangeRate);
                singleEmitter.onSuccess(new GenericResponse("SUCCESS"));
            }catch (Exception ex){
                singleEmitter.onError(new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Failed at update exchange rate"));
            }
        });
    }

    @Override
    public Single<GenericResponse> saveExchangeRate(ExchangeRateRequest exchangeRate) {
        return Single.create( singleEmitter -> {
            try {
                repository.save(new ExchangeRate(exchangeRate.getOriginCurrency(),
                        exchangeRate.getTargetCurrency(),
                        exchangeRate.getRate()));
                singleEmitter.onSuccess(new GenericResponse("SUCCESS"));
            }catch (Exception ex){
                singleEmitter.onError(new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Failed at save exchange rate"));
            }
        });
    }
}
