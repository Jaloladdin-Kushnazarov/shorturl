package org.example.shorturl.entities;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Url extends Auditable {
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "path", unique = true, nullable = false)
    private String url;

    @Column(name = "description", length = 400)
    private String description;

    @Future
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
}