package com.bcp.exchangeRate.application.domains.entities;

import com.bcp.exchangeRate.application.domains.responses.ExchangeResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "ExchangeRate")
public class ExchangeRate {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "originCurrency", nullable = false)
    String originCurrency;
    @Column(name = "targetCurrency", nullable = false)
    String targetCurrency;
    @Column(name = "rate", nullable = false)
    Double rate;

    public ExchangeResponse calculateExchange(Double amount){
        return new ExchangeResponse(
                this.originCurrency,
                amount,
                this.targetCurrency,
                amount*this.rate,
                this.rate
        );
    }
}
