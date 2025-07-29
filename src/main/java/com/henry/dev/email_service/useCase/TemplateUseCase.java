package com.henry.dev.email_service.useCase;

import com.henry.dev.email_service.domain.EmailTemplate;
import com.henry.dev.email_service.domain.request.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateUseCase {

    final String defaultSender = "henry.azevedo06@gmail.com";
    private final JavaMailSender mailSender;

    public EmailTemplate getTemplate(Email email) {
        log.info("Gerando template para: {}", email.getTitle());
        String body =
                email.getMailType().getInMail() +
                        "<h2>" + email.getShift().getMessage() + "<br>" +
                        email.getContent() + "</h2>";


        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setSubject(email.getTitle());
        emailTemplate.setBody(body);

        log.info("Email Template: {}", emailTemplate);
        return emailTemplate;
    }
}
