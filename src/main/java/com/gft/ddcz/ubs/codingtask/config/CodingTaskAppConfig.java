package com.gft.ddcz.ubs.codingtask.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.time.Clock;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

@Configuration
public class CodingTaskAppConfig {

    @Bean
    MessageSource messageSource() {
        var messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames("messages");
        return messageSource;
    }

    @Bean
    Clock clock() {
        var date = LocalDate.of(2020, 10, 9)
                .atStartOfDay()
                .toInstant(UTC);
        return Clock.fixed(date, UTC);
    }
}
