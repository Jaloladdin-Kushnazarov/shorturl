package org.example.shorturl;

import org.example.shorturl.config.SpringdocConfig;
import org.example.shorturl.config.security.SessionUser;
import org.example.shorturl.sevises.UrlServise;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableAsync
public class ShortUrlApplication {
        private final UrlServise urlServise;

    public ShortUrlApplication(UrlServise urlServise) {
        this.urlServise = urlServise;
    }
    public static void main(String[] args) {

        SpringApplication.run(ShortUrlApplication.class, args);
    }



    @Bean
    public AuditorAware<Long> getAuditor(SessionUser sessionUser) {
        return () -> Optional.of(sessionUser.id());
    }


}
