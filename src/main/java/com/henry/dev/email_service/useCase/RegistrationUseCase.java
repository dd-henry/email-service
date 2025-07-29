package com.henry.dev.email_service.useCase;

import com.henry.dev.email_service.domain.request.Email;
import com.henry.dev.email_service.repository.ports.RegistrationRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationUseCase {

    private final RegistrationRepositoryPort repository;

    public void register(Email email) {
        repository.register(email);
    }
}
