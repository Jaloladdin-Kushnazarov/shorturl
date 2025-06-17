package org.example.shorturl.sevises;

import lombok.NonNull;
import org.example.shorturl.entities.AuthUser;
import org.example.shorturl.entities.AuthUserOtp;

public interface AuthUserOtpService {

    AuthUserOtp create(@NonNull  AuthUserOtp authUserOtp);

    AuthUserOtp createOTP(AuthUser authUser);

}
