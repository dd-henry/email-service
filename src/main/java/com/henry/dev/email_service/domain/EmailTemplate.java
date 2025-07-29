package com.henry.dev.email_service.domain;

import lombok.Data;

@Data
public class EmailTemplate {
    private String subject;
    private String body;
}
