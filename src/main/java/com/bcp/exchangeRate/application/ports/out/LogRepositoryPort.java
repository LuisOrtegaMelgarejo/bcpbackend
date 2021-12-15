package com.bcp.exchangeRate.application.ports.out;

import com.bcp.exchangeRate.application.domains.entities.Log;
import io.reactivex.Single;

public interface LogRepositoryPort {
   Single<Log> saveLog(String user, String method, String resource);
}
