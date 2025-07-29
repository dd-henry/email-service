package com.henry.dev.email_service.utils;

import com.henry.dev.email_service.domain.request.Email;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class DBUtils {

    public static MapSqlParameterSource getRegistrationParams(Email request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("to", request.getTo());
        params.addValue("subject", request.getTitle());
        params.addValue("body", request.getContent());
        params.addValue("type", request.getMailType().getCode());
        params.addValue("frequency", request.getFrequency().getDescription());
        params.addValue("daysOfMonth", request.getDays());
        params.addValue("daysOfWeek", request.getDaysOfWeek());
        params.addValue("shift", request.getShift().getDescription());
        return params;
    }

    public static MapSqlParameterSource getScheduleParams(Email email) {
        var params = new MapSqlParameterSource();
        params.addValue("destinatario", email.getTo());
        params.addValue("assunto", email.getTitle());
        params.addValue("tipoMensagemId", email.getMailType().getCode());
        params.addValue("periodicidade", email.getFrequency().getDescription());
        params.addValue("turno", email.getShift().getDescription());
        return params;
    }
}
