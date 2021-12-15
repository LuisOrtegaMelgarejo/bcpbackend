package com.bcp.exchangeRate.application.domains.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExchangeResponse {
    String originCurrency;
    Double amount;
    String targetCurrency;
    Double amountExchanged;
    Double rate;
}
