package com.henry.dev.email_service.domain;

import com.henry.dev.email_service.domain.enums.MailType;
import lombok.Builder;

import java.util.UUID;

@Builder
public record Reminder(
        UUID id,
        String to,
        String title,
        String content,
        MailType mailType
) {}