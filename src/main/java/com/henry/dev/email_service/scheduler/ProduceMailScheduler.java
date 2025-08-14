package com.henry.dev.email_service.scheduler;

import com.henry.dev.email_service.integration.amq.producer.EmailProducer;
import com.henry.dev.email_service.domain.enums.Shift;
import com.henry.dev.email_service.domain.request.Email;
import com.henry.dev.email_service.repository.ports.ScheduledEmailsRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProduceMailScheduler {
    private final ScheduledEmailsRepositoryPort repository;
    private final EmailProducer producer;

    @Scheduled(cron = "0 0 1,6,9,12,15,18 * * *")
//    @Scheduled(cron = "0 */1 * * * *")
    public void execute() {
        log.info("Iniciando verificação de emails agendados...");
        Shift actualShift = getActualShift();
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        int dayOfMonth = today.getDayOfMonth();


        List<Email> emails = repository.getAllActiveMails();
        if (emails.isEmpty()) {
            return;
        }

        for (Email email : emails) {
            if (email.getShift() != actualShift) continue;
            try {
                verifyShiftAndSend(dayOfWeek, dayOfMonth, today, email);
            } catch (Exception e) {
                log.error("Error producing mail message: {}{}", email, e.getMessage());
            }
        }
    }


    private void verifyShiftAndSend(DayOfWeek dayOfWeek, int dayOfMonth, LocalDate today, Email email) {
        switch (email.getFrequency()) {
            case DAILY:
                if (email.getLastSent() != null && email.getLastSent().getDayOfMonth() >= today.getDayOfMonth()) {
                    log.info("Email already sent today: {}", email.getTitle());
                    break;
                }
                producer.produceEmail(email);
                break;

            case WEEKLY:
                if (email.getDaysOfWeek() != null && java.util.Arrays.stream(email.getDaysOfWeek().split(",")).map(Integer::parseInt).anyMatch(d -> d == dayOfWeek.getValue())) {
                    producer.produceEmail(email);
                }
                break;

            case MONTLHY:
                if (email.getIntegerDays() != null && email.getIntegerDays().contains(dayOfMonth) && email.getLastSent().getMonth().equals(today.getMonth().minus(1))) {
                    producer.produceEmail(email);
                }
                break;

            case CUSTOM:
                if (email.getIntegerDays() != null && email.getIntegerDays().contains(dayOfMonth)) {
                    producer.produceEmail(email);
                }
                break;

            case UNIQUE:
                if (email.getIntegerDays() != null && email.getIntegerDays().contains(dayOfMonth)) {
                    repository.updateStatus(email, false);
                    producer.produceEmail(email);
                }
                break;
        }
    }

    private Shift getActualShift() {
        int hora = LocalTime.now().getHour();
        if (hora >= 5 && hora < 8) return Shift.DAWN;
        if (hora >= 8 && hora < 11) return Shift.MORNING;
        if (hora >= 11 && hora < 14) return Shift.LUNCH;
        if (hora >= 14 && hora < 18) return Shift.AFTERNOON;
        return Shift.EVENING;
    }
}
