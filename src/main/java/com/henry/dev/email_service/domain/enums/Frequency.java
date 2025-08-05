package com.henry.dev.email_service.domain.enums;

import lombok.Getter;

@Getter
public enum Frequency {

    UNIQUE(0, "UNICA"),
    DAILY(1, "DIARIO"),
    WEEKLY(2, "SEMANAL"),
    MONTLHY(3, "MENSAL"),
    CUSTOM(4, "CUSTOMIZADO");

    private final int code;
    private final String description;

    Frequency(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Frequency fromDescription(String description) {
        for (Frequency freq : Frequency.values()) {
            if (freq.getDescription().equalsIgnoreCase(description)) {
                return freq;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + description);
    }
}
