package org.example.shorturl.sevises.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.shorturl.entities.AuthUser;
import org.example.shorturl.entities.AuthUserOtp;
import org.example.shorturl.repositories.AuthUserOtpRepository;
import org.example.shorturl.sevises.AuthUserOtpService;
import org.example.shorturl.utils.BaseUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthUserOtpServiceImpl implements AuthUserOtpService {

    private final AuthUserOtpRepository authUserOtpRepository;
    private final BaseUtil baseUtil;


    @Override
    public AuthUserOtp create(@NonNull AuthUserOtp authUserOtp) {
        return authUserOtpRepository.save(authUserOtp);
    }

    @Override
    public AuthUserOtp createOTP(AuthUser authUser) {
         AuthUserOtp authUserOtp = AuthUserOtp.builder()
                 .code(baseUtil.generateOtp(authUser.getId()))
                 .userId(authUser.getId())
                 .expiryAt(LocalDateTime.now().plusMinutes(10))
                 .build();

        return create(authUserOtp);
    }
}
