package org.example.shorturl;

import org.example.shorturl.config.security.SessionUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class ShortUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortUrlApplication.class, args);
    }



    @Bean
    public AuditorAware<Long> getAuditor(SessionUser sessionUser) {
        return () -> Optional.of(sessionUser.id());
    }


}
