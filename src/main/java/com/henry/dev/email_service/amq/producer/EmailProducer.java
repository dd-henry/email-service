package com.henry.dev.email_service.amq.producer;

import com.henry.dev.email_service.domain.request.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    public void produceEmail(Email email) {
        String queue = "email-queue";

        System.out.println("Producing email: " + email);
        rabbitTemplate.convertAndSend(queue, email);
    }
}
