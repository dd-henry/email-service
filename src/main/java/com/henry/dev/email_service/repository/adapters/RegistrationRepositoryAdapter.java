package com.henry.dev.email_service.repository.adapters;

import com.henry.dev.email_service.domain.request.Email;
import com.henry.dev.email_service.repository.ports.RegistrationRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.henry.dev.email_service.repository.queries.QueriesRepository.PERSIST_MAIL_SQL;
import static com.henry.dev.email_service.utils.DBUtils.getRegistrationParams;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RegistrationRepositoryAdapter implements RegistrationRepositoryPort {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    @Transactional
    public void register(Email request) {
        log.info("Persisting mail: {}", request.getTitle());
        MapSqlParameterSource params = getRegistrationParams(request);

        jdbc.update(PERSIST_MAIL_SQL, params);
        log.info("Email registered successfully: {}", request.getTitle());
    }


}
