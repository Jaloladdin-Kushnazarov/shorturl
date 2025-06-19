package org.example.shorturl.sevises.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.shorturl.config.security.SessionUser;
import org.example.shorturl.dtos.UrlCreateDto;
import org.example.shorturl.dtos.url.DailyReport;
import org.example.shorturl.dtos.url.UrlReport;
import org.example.shorturl.dtos.url.WeeklyReport;
import org.example.shorturl.entities.Url;
import org.example.shorturl.mappers.UrlMapper;
import org.example.shorturl.repositories.UrlRepository;
import org.example.shorturl.sevises.UrlServise;
import org.example.shorturl.utils.BaseUtil;
import org.example.shorturl.utils.MailSenderServise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org..security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UrlServiseImpl implements UrlServise {

    private final UrlMapper urlMapper;
    private final BaseUtil baseUtil;
    private final SessionUser sessionUser;
    private final UrlRepository urlRepository;
    private final MailSenderServise mailSenderServise;

    @Override
    public Url shortUrl(UrlCreateDto urlCreateDto) {
        Url url = urlMapper.toEntity(urlCreateDto);
        url.setCode(baseUtil.shortenUrlCode(sessionUser.id()));

        if (url.getExpiresAt() == null)
            url.setExpiresAt(LocalDateTime.now().plusDays(1));
        return urlRepository.save(url);
    }


    @Override
    public Url getByCode(@NonNull String code) {
        Url url = urlRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Url bycode not found"));

        if (url.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException(" shortened Url expired");
        }
        return url;
    }

    @Override
    public Page<Url> getPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return urlRepository.findAllByUserID(sessionUser.id(), pageable);
    }

    @Override
    public WeeklyReport getWeeklyReport() {
        LocalDate today = LocalDate.now();
        LocalDate lastMonday = today.minusWeeks(1).with(DayOfWeek.MONDAY);
        LocalDate lastSunday = today.minusWeeks(1).with(DayOfWeek.SUNDAY);

        LocalDateTime from = lastMonday.atStartOfDay();              // 00:00
        LocalDateTime to = lastSunday.atTime(LocalTime.MAX);         // 23:59:59.999999999

        AtomicInteger count = new AtomicInteger(0);

        List<Url> urls = urlRepository.findAllByUSer(sessionUser.id(), from, to);
        List<DailyReport> dailyReports = new ArrayList<>();
        urls.stream()
                .map(UrlReport::new)
                .collect(Collectors.groupingBy(urelReport -> urelReport.getDayOfWeek().getValue()))
                .forEach((dayNumber, urlsReports) -> {
                    dailyReports.add(new DailyReport(dayNumber, urlsReports));
                    count.addAndGet(urlsReports.size());
                });
        return new WeeklyReport(
                baseUtil.formatter(from),
                baseUtil.formatter(to),
                dailyReports,
                count.get());
    }

    @Override
    public void sendWeaklyReport() {
        WeeklyReport weeklyReport = getWeeklyReport();

        String email = sessionUser.user().getEmail();
        Map<String, Object> model = new HashMap<>();
        model.put("to", email);
        model.put("username", sessionUser.user().getUsername());
        model.put("from", weeklyReport.getFrom());
        model.put("to", weeklyReport.getTo());
        model.put("count", weeklyReport.getCount());
        model.put("dailyReports", weeklyReport.getDailyReports());
        mailSenderServise.sendWeeklyReport(model);
    }
}
