package org.example.shorturl.config.security;


import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.shorturl.entities.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final AuthUser authUser;

    public CustomUserDetails(@Nonnull AuthUser authUser) {
        this.authUser = authUser;
        this.id = authUser.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + authUser.getRole())
        );
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return authUser.isActive(); // yoki true
    }

    @Override
    public boolean isAccountNonLocked() {
        return authUser.isActive(); // yoki true
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return authUser.isActive(); // yoki true
    }

    @Override
    public boolean isEnabled() {
        return authUser.isActive();
    }

    public Long getId() {
        return id;
    }
}