package org.example.shorturl.dtos.url;

import java.util.List;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeeklyReport  {

    private String from;                   //qaysi kundan
    private String to;                     //qaysi kungacha
    private List<DailyReport> dailyReports;
    private Integer count;

    public WeeklyReport(String from, String to, List<DailyReport> dailyReports, Integer count) {
        this.from = from;
        this.to = to;
        this.dailyReports = dailyReports;
        this.count = count;
    }
}
