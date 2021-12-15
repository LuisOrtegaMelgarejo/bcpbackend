package com.bcp.exchangeRate.application.domains.entities;

import com.bcp.exchangeRate.application.domains.responses.ExchangeResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ExchangeRate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "originCurrency", nullable = false)
    String originCurrency;
    @Column(name = "targetCurrency", nullable = false)
    String targetCurrency;
    @Column(name = "rate", nullable = false)
    Double rate;

    public ExchangeRate(String originCurrency,
                        String targetCurrency,
                        Double rate) {
        this.originCurrency = originCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }

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
