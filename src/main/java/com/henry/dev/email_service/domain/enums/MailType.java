package com.henry.dev.email_service.domain.enums;

import lombok.Getter;

@Getter
public enum MailType {
    FINANCE(1, "<H1>Lembrete Financeiro \uD83D\uDCB8</H1>"),
    GENERAL(2, "<H1>Lembrete Geral \uD83D\uDCCB</H1>"),
    MESSAGE(3, "<H1>Mensagem</H1>"),
    STUDY(4, "<H1>Lembrete de Estudo \uD83D\uDCAC</H1>"),
    EVENT(5, "<H1>Lembrete de Evento \uD83C\uDF89</H1>"),
    ;

    private final int code;
    private final String inMail;

    public static MailType fromCode(int code) {
        for (MailType type : MailType.values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Código inválido para MailType: " + code);
    }

    MailType(int code, String inMail) {
        this.code = code;
        this.inMail = inMail;
    }
}