package org.example.shorturl.sevises;

import org.example.shorturl.entities.Url;
import org.example.shorturl.dtos.UrlCreateDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface UrlServise {

    Url shortUrl(@PathVariable UrlCreateDto urlCreateDto);

}
