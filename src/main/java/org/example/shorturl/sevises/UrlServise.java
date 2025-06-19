package org.example.shorturl.sevises;

import lombok.NonNull;
import org.example.shorturl.dtos.url.WeeklyReport;
import org.example.shorturl.entities.Url;
import org.example.shorturl.dtos.UrlCreateDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

public interface UrlServise {

    Url shortUrl(@PathVariable UrlCreateDto urlCreateDto);

    Url  getByCode(@NonNull String code);

    Page<Url> getPage(int page, int size);

    WeeklyReport getWeeklyReport();

    void sendWeaklyReport();
}
