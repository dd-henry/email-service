package com.henry.dev.email_service.useCase;

import com.henry.dev.email_service.domain.EmailTemplate;
import com.henry.dev.email_service.domain.request.Email;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendMailUseCase {

    final String defaultSender = "henry.azevedo06@gmail.com";
    private final JavaMailSender mailSender;
    private final TemplateUseCase templateUseCase;

    @Transactional
    public void sendEmail(Email email) {
        try {
            EmailTemplate template = templateUseCase.getTemplate(email);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setFrom(defaultSender);
            helper.setTo(email.getTo());
            helper.setSubject(template.getSubject());
            helper.setText(template.getBody(), true);
            helper.getMimeMessage().addHeader("Importance", "High");

            mailSender.send(mimeMessage);

            log.info("Email enviado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao enviar email: " + e.getMessage());
        }
    }
}
