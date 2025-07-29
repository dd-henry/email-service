package com.henry.dev.email_service.domain.enums;

import lombok.Getter;

@Getter
public enum Shift {
    DAWN(1, "AMANHECER", "Bom dia!, que tal começar o dia com um café? ☕"),
    MORNING(2, "MANHA", "Bom dia!, vamos começar o dia com energia! ☀️"),
    LUNCH(3, "ALMOCO", "Como estamos indo até agora? Chegamos ao almoço! 🍽️"),
    AFTERNOON(4, "TARDE", "Boa tarde! 🌞" ),
    EVENING(5, "NOITE", "Boa noite! 🌙"),;

    private final int code;
    private final String description;
    private final String message;

    Shift(int code, String description, String message) {
        this.code = code;
        this.description = description;
        this.message = message;
    }

    public static Shift fromDescription(String description) {
        for (Shift shift : Shift.values()) {
            if (shift.getDescription().equalsIgnoreCase(description)) {
                return shift;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + description);
    }
}
