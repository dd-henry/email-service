package com.henry.dev.email_service.repository.queries;

import org.springframework.stereotype.Component;

@Component
public class QueriesRepository {

    private QueriesRepository() {
    }

    public final static String PERSIST_MAIL_SQL = """
            INSERT INTO lembretes
                (destinatario, assunto, mensagem, tipo_mensagem_id, periodicidade, dias_mes, dias_semana, turno, ativo)
            VALUES (:to, :subject, :body, :type, :frequency, :daysOfMonth, :daysOfWeek, :shift, true)
            """;


    public final static String UPDATE_LAST_SENT_SQL = """
            UPDATE lembretes SET ultimo_envio = NOW()
                WHERE destinatario =
                      :destinatario and assunto = :assunto and tipo_mensagem_id = :tipoMensagemId
                    and periodicidade = :periodicidade and turno = :turno
            """;

    public final static String GET_ACTIVE_MAILS_SQL = "SELECT * FROM lembretes WHERE ativo = true";


}
