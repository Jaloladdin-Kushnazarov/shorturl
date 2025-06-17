package org.example.shorturl.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthUserOtp extends Auditable{

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private Long userId;

    @Future
    @Column(nullable = false)
    private LocalDateTime expiryAt;


}
