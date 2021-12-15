package com.bcp.exchangeRate.application.domains.requests;

import lombok.Getter;

@Getter
public class ExchangeRequest {
    String originCurrency;
    String targetCurrency;
    Double amount;
}
