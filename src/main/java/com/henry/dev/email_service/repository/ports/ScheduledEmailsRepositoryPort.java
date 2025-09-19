package com.henry.dev.email_service.repository.ports;

import com.henry.dev.email_service.domain.request.Email;

import java.util.List;

public interface ScheduledEmailsRepositoryPort {
    List<Email> getAllActiveMails();
    void setLastSent(Email email);
    void updateStatus(Email email, boolean status);
    void registerSendAttempt(Email email);
}
