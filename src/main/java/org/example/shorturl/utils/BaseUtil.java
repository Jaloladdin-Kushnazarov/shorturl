package org.example.shorturl.utils;

import com.google.common.hash.Hashing;
import io.micrometer.common.lang.NonNull;
import org.example.shorturl.entities.AuthUserOtp;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

@Component
public class BaseUtil {

    private final Base64.Encoder encoder = Base64.getUrlEncoder();
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String generateOtp(Long userId) {
        return   encoder.encodeToString((UUID.randomUUID().toString() + userId).getBytes());
    }


    public String shortenUrlCode(Long userId) {
        return Hashing.murmur3_32_fixed().hashString( UUID.randomUUID().toString()  + userId, StandardCharsets.UTF_8).toString();

    }


    public String formatter(LocalDateTime localDateTime) {
        return FORMATTER.format(localDateTime);
    }
}
