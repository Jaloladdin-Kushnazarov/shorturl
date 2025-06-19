package org.example.shorturl.config.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class SessionUser {

    public CustomUserDetails user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails customUserDetails) {
                return customUserDetails;
            } else {
                log.warn("Principal is not instance of CustomUserDetails, actual type: {}", principal.getClass());
            }
        } else {
            log.warn("Authentication not found or not authenticated.");
        }

        return null;
    }

    public Long id() {
        CustomUserDetails user = user();
        return user != null ? user.getId() : -1L;
    }
}


