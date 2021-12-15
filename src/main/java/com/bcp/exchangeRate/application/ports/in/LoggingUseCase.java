package com.bcp.exchangeRate.application.ports.in;

import com.bcp.exchangeRate.application.domains.entities.Log;
import io.reactivex.Single;

import javax.servlet.http.HttpServletRequest;

public interface LoggingUseCase {
    Single<Log> addLogFromRequest(String user, HttpServletRequest req);
}
