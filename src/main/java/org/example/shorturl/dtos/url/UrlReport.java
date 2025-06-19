package org.example.shorturl.dtos.url;


import lombok.Getter;
import lombok.Setter;
import org.example.shorturl.entities.Url;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Getter
@Setter
public class UrlReport {
    private String code;
    private String url;
    private String description;
    private DayOfWeek dayOfWeek;
    private boolean expired;


    public UrlReport(Url url){
        this.code = url.getCode();
        this.url = url.getUrl();
        this.description = url.getDescription();
        this.dayOfWeek = url.getCreatedAt().getDayOfWeek();
        this.expired = url.getExpiresAt()  .isBefore(LocalDateTime.now());
    }
}
