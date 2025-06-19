package org.example.shorturl.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.shorturl.dtos.UrlCreateDto;
import org.example.shorturl.dtos.url.WeeklyReport;
import org.example.shorturl.entities.Url;
import org.example.shorturl.sevises.UrlServise;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlServise urlServise;


    @PreAuthorize("isAuthenticated ()")
    @PostMapping("/api/url")
    public ResponseEntity<Url> shortenUrl(@Valid @RequestBody UrlCreateDto urlCreateDto) {
        return ResponseEntity.ok(urlServise.shortUrl(urlCreateDto));
    }



    @PreAuthorize ("isAuthenticated()")
    @GetMapping("/api/url/{code}")
    public ResponseEntity<Url> get(@PathVariable String code) {
        return ResponseEntity.ok(urlServise.getByCode(code));
    }

        @PreAuthorize ("isAuthenticated()")
        @GetMapping("/api/url")
        public ResponseEntity<Page<Url>> getPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
            return ResponseEntity.ok(urlServise.getPage(page, size));
        }


    @PreAuthorize("permitAll()")
    @GetMapping("/{code}")
    public void redirectUrl(@PathVariable String code, HttpServletResponse response) throws IOException {
        Url url = urlServise.getByCode(code);
        response.sendRedirect(url.getUrl());
    }



    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/url/report")
    public WeeklyReport getWeeklyReport() throws IOException {
      return urlServise.getWeeklyReport();
    }
}
