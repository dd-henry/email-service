-- changeset henry:4
CREATE TABLE historico_envios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    lembrete_id UUID NOT NULL,
    data_envio TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    status VARCHAR(50) NOT NULL,
    destinatario_enviado VARCHAR(255),
    CONSTRAINT fk_historico_envios_lembrete_id
      FOREIGN KEY(lembrete_id)
          REFERENCES lembretes(id)
);