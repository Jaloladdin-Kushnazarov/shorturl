package org.example.shorturl.sevises;


import lombok.NonNull;
import org.example.shorturl.dtos.auth.AuthUserCreateDto;
import org.example.shorturl.dtos.auth.GenerateTokenRequest;

public interface AuthUserServise {

    String register(@NonNull AuthUserCreateDto dto);

    String generateToken(@NonNull GenerateTokenRequest request);

    String activateAccount(@NonNull String code);

}
