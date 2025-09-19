-- changeset henry:1
CREATE TABLE dominio_tipo_mensagem
(
    id        INTEGER PRIMARY KEY,
    descricao VARCHAR(30) NOT NULL UNIQUE
);

-- changeset henry:2
INSERT INTO dominio_tipo_mensagem (id, descricao)
VALUES (1, 'Financeira'),
       (2, 'Geral'),
       (3, 'Mensagem'),
       (4, 'Estudos'),
       (5, 'Evento');

-- changeset henry:3
CREATE TABLE lembretes
(
    id               UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
    destinatario     VARCHAR(255) NOT NULL,
    assunto          VARCHAR(255) NOT NULL,
    mensagem         TEXT         NOT NULL,
    tipo_mensagem_id INTEGER      NOT NULL,
    periodicidade    VARCHAR(30)  NOT NULL,
    dias_mes         VARCHAR(255),
    dias_semana      VARCHAR(255),
    turno            VARCHAR(20)  NOT NULL,
    ativo            BOOLEAN      NOT NULL    DEFAULT TRUE,
    ultimo_envio TIMESTAMP WITH TIME ZONE,
    criado_em        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    atualizado_em    TIMESTAMP WITH TIME ZONE,

    CONSTRAINT fk_tipo_mensagem
        FOREIGN KEY (tipo_mensagem_id)
            REFERENCES dominio_tipo_mensagem (id)
            ON DELETE RESTRICT
            ON UPDATE CASCADE,

    CONSTRAINT chk_periodicidade CHECK (periodicidade IN ('DIARIO', 'SEMANAL', 'MENSAL', 'CUSTOMIZADO')),
    CONSTRAINT chk_turno CHECK (turno IN ('AMANHECER', 'MANHA', 'ALMOCO', 'TARDE', 'NOITE'))
);
