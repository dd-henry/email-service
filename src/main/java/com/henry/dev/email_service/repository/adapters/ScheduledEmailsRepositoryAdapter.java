package com.henry.dev.email_service.repository.adapters;

import com.henry.dev.email_service.domain.request.Email;
import com.henry.dev.email_service.repository.ports.ScheduledEmailsRepositoryPort;
import com.henry.dev.email_service.repository.rowmapper.EmailRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.henry.dev.email_service.repository.queries.QueriesRepository.GET_ACTIVE_MAILS_SQL;
import static com.henry.dev.email_service.repository.queries.QueriesRepository.UPDATE_LAST_SENT_SQL;
import static com.henry.dev.email_service.utils.DBUtils.getScheduleParams;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ScheduledEmailsRepositoryAdapter implements ScheduledEmailsRepositoryPort {
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public List<Email> getAllActiveMails() {
        return jdbc.query(GET_ACTIVE_MAILS_SQL, new EmailRowMapper());
    }

    public void setLastSent(Email email) {
        jdbc.update(UPDATE_LAST_SENT_SQL, getScheduleParams(email));
        log.info("Updated last sent time for email: {}", email.getTitle());
    }


}


