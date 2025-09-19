package com.henry.dev.email_service.integration.amq.producer;

import com.henry.dev.email_service.domain.request.Email;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmailProducer {

    private final SqsTemplate sqsTemplate;

    @Value("${app.queues.email-reminder}")
    private String queueName;

    public void produceEmail(Email reminder) {
        log.info("Enviando lembrete para a fila {}: {}", queueName, reminder);

        sqsTemplate.send(to -> to
                .queue(queueName)
                .payload(reminder.toReminder())
        );
        log.info("Lembrete enviado com sucesso.");
    }
}
