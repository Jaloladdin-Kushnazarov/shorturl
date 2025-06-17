package org.example.shorturl.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppErrorDto {
    private String friendlyMessage;
    private Object developerMessage; // Validationda Map, Runtime'da String bo'lishi mumkin
    private String errorPath;
    private Integer statusCode;


    // Faqat user-friendly (tashqi) xatolar uchun qisqa konstruktor
    public AppErrorDto(String friendlyMessage, String errorPath, Integer statusCode) {
        this.friendlyMessage = friendlyMessage;
        this.developerMessage = friendlyMessage; // developerMessage'ga ham foydalanuvchi xabarini qo'yamiz
        this.errorPath = errorPath;
        this.statusCode = statusCode;
    }


    @Override
    public String toString() {
        return String.format("""
            friendlyMessage: %s
            statusCode: %d
            errorPath: %s
            developerMessage: `%s`
            """, friendlyMessage, statusCode, errorPath, developerMessage);
    }
}
