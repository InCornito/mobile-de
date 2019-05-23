package de.mobile;

import de.mobile.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BackendCodingChallengeApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}
