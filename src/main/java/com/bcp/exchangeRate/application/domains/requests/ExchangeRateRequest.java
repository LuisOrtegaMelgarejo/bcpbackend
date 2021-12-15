package com.bcp.exchangeRate.application.domains.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateRequest {
    String originCurrency;
    String targetCurrency;
    Double rate;
}
