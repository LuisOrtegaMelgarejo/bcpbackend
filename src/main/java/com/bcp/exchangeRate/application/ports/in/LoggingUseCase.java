package com.bcp.exchangeRate.application.ports.in;

import io.reactivex.Single;

import javax.servlet.http.HttpServletRequest;

public interface LoggingUseCase {
    Single<Void> addLogFromRequest(String user, HttpServletRequest req);
}
