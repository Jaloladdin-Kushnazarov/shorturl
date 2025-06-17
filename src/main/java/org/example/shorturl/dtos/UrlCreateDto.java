package org.example.shorturl.dtos;

import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.example.shorturl.entities.Url;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Url}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlCreateDto implements Serializable {
    String url;
    String description;
    @Future
    LocalDateTime expiresAt;
}