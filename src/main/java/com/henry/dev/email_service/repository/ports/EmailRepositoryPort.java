package com.henry.dev.email_service.repository.ports;

import java.util.UUID;

public interface EmailRepositoryPort {
    void confirmEmail(UUID token);
}
