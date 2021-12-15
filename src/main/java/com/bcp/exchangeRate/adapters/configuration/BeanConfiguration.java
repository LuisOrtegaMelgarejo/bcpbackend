package com.bcp.exchangeRate.adapters.configuration;

import com.bcp.exchangeRate.ExchangeRateApplication;
import com.bcp.exchangeRate.adapters.persistance.LogRepository;
import com.bcp.exchangeRate.application.services.ExchangeService;
import com.bcp.exchangeRate.adapters.persistance.ExchangeRateRepository;
import com.bcp.exchangeRate.application.services.LoggingService;
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
    @Bean
    LoggingService loggingService(LogRepository repository) {
        return new LoggingService(repository);
    }

}
