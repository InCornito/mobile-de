package de.mobile.config;

import de.mobile.service.LocaleProvider;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"de.mobile"})
@Configuration
public class AppConfig {

    @Bean
    public LocaleProvider localeProvider() {
        return LocaleContextHolder::getLocale;
    }

}
