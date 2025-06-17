package org.example.shorturl.sevises.impl;

import lombok.RequiredArgsConstructor;
import org.example.shorturl.config.security.SessionUser;
import org.example.shorturl.dtos.UrlCreateDto;
import org.example.shorturl.entities.Url;
import org.example.shorturl.mappers.UrlMapper;
import org.example.shorturl.repositories.UrlRepository;
import org.example.shorturl.sevises.UrlServise;
import org.example.shorturl.utils.BaseUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UrlServiseImpl implements UrlServise {

    private final UrlMapper urlMapper;
    private final BaseUtil baseUtil;
    private final SessionUser sessionUser;
    private final UrlRepository urlRepository;


    @Override
    public Url shortUrl(UrlCreateDto urlCreateDto) {
        Url url = urlMapper.toEntity(urlCreateDto);
        url.setCode(baseUtil.shortenUrlCode(sessionUser.id()));

        if(url.getExpiresAt() == null)
            url.setExpiresAt(LocalDateTime.now().plusDays(1));

        return urlRepository.save(url);
    }
}
