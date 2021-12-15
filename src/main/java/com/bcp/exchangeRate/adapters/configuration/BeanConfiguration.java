package com.bcp.exchangeRate.adapters.configuration;

import com.bcp.exchangeRate.ExchangeRateApplication;
import com.bcp.exchangeRate.application.services.ExchangeService;
import com.bcp.exchangeRate.adapters.persistance.ExchangeRateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ExchangeRateApplication.class)
public class BeanConfiguration {

    @Bean
    ExchangeService exchangeService(ExchangeRateRepository repository) {
        return new ExchangeService(repository);
    }

}
