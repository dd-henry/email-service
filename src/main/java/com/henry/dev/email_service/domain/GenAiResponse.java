package com.henry.dev.email_service.domain;

import lombok.Data;

import java.util.List;

@Data
public class GenAiResponse {
    private List<Choice> choices;
}
