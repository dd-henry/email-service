package com.henry.dev.email_service.repository.rowmapper;

import com.henry.dev.email_service.domain.enums.Frequency;
import com.henry.dev.email_service.domain.enums.MailType;
import com.henry.dev.email_service.domain.enums.Shift;
import com.henry.dev.email_service.domain.request.Email;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class EmailRowMapper implements RowMapper<Email> {

    @Override
    public Email mapRow(ResultSet rs, int rowNum) throws SQLException {
        Email email = new Email();
        email.setTo(rs.getString("destinatario"));
        email.setTitle(rs.getString("assunto"));
        email.setContent(rs.getString("mensagem"));
        email.setMailType(MailType.fromCode(rs.getInt("tipo_mensagem_id")));
        email.setShift(Shift.fromDescription(rs.getString("turno")));
        email.setFrequency(Frequency.fromDescription(rs.getString("periodicidade")));
        email.setDays(rs.getString("dias_mes"));
        email.setDaysOfWeek(rs.getString("dias_semana"));
        email.setLastSent(rs.getTimestamp("ultimo_envio") != null ? rs.getTimestamp("ultimo_envio").toLocalDateTime() : null);
        return email;
    }
}