package com.bcp.exchangeRate.application.services;

import com.bcp.exchangeRate.application.ports.in.LoggingUseCase;
import com.bcp.exchangeRate.application.ports.out.LogRepositoryPort;
import io.reactivex.Single;

import javax.servlet.http.HttpServletRequest;

public class LoggingService implements LoggingUseCase {

    LogRepositoryPort logRepositoryPort;

    public LoggingService(LogRepositoryPort logRepositoryPort) {
        this.logRepositoryPort = logRepositoryPort;
    }
    @Override
    public Single<Void> addLogFromRequest(String user, HttpServletRequest req) {
        System.out.println("Lllegamos");
        logRepositoryPort.saveLog(user,req.getMethod(),req.getRequestURI());
        return Single.create(singleEmitter -> {
            logRepositoryPort.saveLog(user,req.getMethod(),req.getRequestURI());
        });
    }
}
