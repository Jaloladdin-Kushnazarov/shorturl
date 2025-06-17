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

    public UserDetails user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                return userDetails;
            }
        }

        return null;
    }

    public Long id() {
        UserDetails user = user();
        if (Objects.isNull(user)) {
            log.warn("User is not authenticated or not found in SecurityContext");
            return -1L;
        }

        if (user instanceof CustomUserDetails customUserDetails) {
            return customUserDetails.getId();
        }

        log.warn("Principal is not instance of CustomUserDetails");
        return -1L;
    }
}


