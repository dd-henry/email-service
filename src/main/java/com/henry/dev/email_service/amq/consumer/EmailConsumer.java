package com.henry.dev.email_service.amq.consumer;

import com.henry.dev.email_service.domain.request.Email;
import com.henry.dev.email_service.useCase.SendMailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final SendMailUseCase useCase;

    @RabbitListener(queues = "email-queue")
    public void consumeEmail(@Payload Email email) {
        System.out.println("Processing email: " + email.toString());
        useCase.sendEmail(email);
    }

}
