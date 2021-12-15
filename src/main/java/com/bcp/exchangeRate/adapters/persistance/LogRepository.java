package com.bcp.exchangeRate.adapters.persistance;

import com.bcp.exchangeRate.application.domains.entities.Log;
import com.bcp.exchangeRate.application.ports.out.LogRepositoryPort;
import com.bcp.exchangeRate.infrastructure.repository.SpringLogRepository;
import io.reactivex.Single;
import org.springframework.stereotype.Component;

@Component
public class LogRepository implements LogRepositoryPort {

    private SpringLogRepository repository;

    public LogRepository(SpringLogRepository repository) {
        this.repository = repository;
    }

    public Single<Log> saveLog(String user, String method, String resource) {
        return Single.create(singleEmitter -> {
            Log newLog = repository.save(new Log(user,method,resource));
            singleEmitter.onSuccess(newLog);
        });
    }

}
