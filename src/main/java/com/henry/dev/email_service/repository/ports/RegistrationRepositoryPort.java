package com.henry.dev.email_service.repository.ports;

import com.henry.dev.email_service.domain.request.Email;

public interface RegistrationRepositoryPort {
    void register(Email request);
}
