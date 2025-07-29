package com.henry.dev.email_service.domain.request;

import com.henry.dev.email_service.domain.enums.Frequency;
import com.henry.dev.email_service.domain.enums.MailType;
import com.henry.dev.email_service.domain.enums.Shift;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Email {
    private String to;
    private String title;
    private String content;
    private MailType mailType;
    private Shift shift;
    private Frequency frequency;
    private String days;
    private String daysOfWeek;
    private LocalDateTime lastSent;



    public List<Integer> getIntegerDays(){
        if (days == null || days.isEmpty()) {
            return List.of();
        }
        return List.of(days.split(",")).stream()
                .map(String::trim)
                .filter(day -> !day.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }
}
