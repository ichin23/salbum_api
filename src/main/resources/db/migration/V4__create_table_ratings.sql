CREATE TABLE ratings (
    -- Chaves estrangeiras que formam a chave primária composta
    user_id UUID NOT NULL,
    album_id UUID NOT NULL,

    -- Dados da avaliação
    rate NUMERIC(2, 1) NOT NULL,

    -- Colunas de auditoria
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,

    -- Definição da chave primária composta
    CONSTRAINT ratings_pkey PRIMARY KEY (user_id, album_id),

    -- Definição das chaves estrangeiras
    CONSTRAINT fk_ratings_user
        FOREIGN KEY (user_id)
        REFERENCES "users" (id) ON DELETE CASCADE,

    CONSTRAINT fk_ratings_album
        FOREIGN KEY (album_id)
        REFERENCES albums (id) ON DELETE CASCADE
);