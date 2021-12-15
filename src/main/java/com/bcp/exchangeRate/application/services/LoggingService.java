package com.bcp.exchangeRate.application.services;

import com.bcp.exchangeRate.application.domains.entities.Log;
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
    public Single<Log> addLogFromRequest(String user, HttpServletRequest req) {
        return logRepositoryPort.saveLog(user,req.getMethod(),req.getRequestURI());
    }
}
