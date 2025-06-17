package org.example.shorturl.sevises.impl;

import jakarta.validation.constraints.NotNull;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shorturl.config.security.JwtTokenUtil;
import org.example.shorturl.dtos.auth.AuthUserCreateDto;
import org.example.shorturl.dtos.auth.GenerateTokenRequest;
import org.example.shorturl.entities.AuthUser;
import org.example.shorturl.entities.AuthUserOtp;
import org.example.shorturl.mappers.AuthUserMapper;
import org.example.shorturl.repositories.AuthUserOtpRepository;
import org.example.shorturl.repositories.AuthUserRepository;
import org.example.shorturl.sevises.AuthUserOtpService;
import org.example.shorturl.sevises.AuthUserServise;
import org.example.shorturl.utils.MailSenderServise;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUserServiseImpl implements AuthUserServise {

    private final AuthUserMapper authUserMapper;
    private final AuthUserRepository authUserRepository;
    private final AuthenticationManager  authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final MailSenderServise mailSenderServise;
    private final AuthUserOtpService authUserOtpService;
    private final AuthUserOtpRepository authUserOtpRepository;
    private final PasswordEncoder bcryptPasswordEncoder;


    @Override
    public String register(@NotNull AuthUserCreateDto dto) {
        if (authUserRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("Email already in use");
        }
        if(authUserRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new RuntimeException("Username already in use");

        }
        AuthUser authUser = authUserMapper.toEntity(dto);
        authUser.setPassword(bcryptPasswordEncoder.encode(authUser.getPassword()));
        authUser.setRole("USER");
        authUserRepository.save(authUser);
        AuthUserOtp authUserOtp = authUserOtpService.createOTP(authUser);
        Map<String, String> model = new HashMap<>();
        model.put("to", authUser.getEmail());
        model.put("code",  authUserOtp.getCode());
        mailSenderServise.sendActivationMail(model);
        log.info("New user registered: {}", authUser.getUsername());
        return "Success";
    }

    @Override
    public String generateToken(@NotNull GenerateTokenRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticationToken);

        log.info("Token generated for user: {}", username);
        return jwtTokenUtil.generateToken(username);
    }

    @Override
    public String activateAccount(@NonNull String code) {
        AuthUserOtp authUserOtp = authUserOtpRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new IllegalArgumentException("Invalid code: " + code));

        if(authUserOtp.getExpiryAt().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Invalid code: " + code);
        }

        Long userId = authUserOtp.getUserId();
        authUserRepository.activateUser(userId);
        return "Account activated successfully" ;
    }

}