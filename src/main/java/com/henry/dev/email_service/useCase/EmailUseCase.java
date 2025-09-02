package com.henry.dev.email_service.useCase;

import com.henry.dev.email_service.repository.ports.EmailRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailUseCase {

    private final EmailRepositoryPort repository;

    public void confirmEmail(String token) {
        UUID id = UUID.fromString(token);
        repository.confirmEmail(id);
    }
}
