package org.example.shorturl.dtos.url;

import java.time.DayOfWeek;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyReport {
    private String dayName;
    private Integer dayNumber;
    private Integer count;
    private List<UrlReport> reports;


    public DailyReport(Integer dayNumber, List<UrlReport> reports) {
        this. dayNumber = dayNumber;
        this.dayName = DayOfWeek.of(dayNumber) .name();
        this.reports = reports;
        this.count = reports.size();
    }
}
