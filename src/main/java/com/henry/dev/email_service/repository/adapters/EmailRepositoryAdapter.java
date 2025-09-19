package com.henry.dev.email_service.repository.adapters;

import com.henry.dev.email_service.repository.ports.EmailRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EmailRepositoryAdapter implements EmailRepositoryPort {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public void confirmEmail(UUID token) {
        jdbc.update("UPDATE historico_envios SET status = 'VISUALIZADO' WHERE lembrete_id = :token",
            Map.of("token", token));
    }
}
