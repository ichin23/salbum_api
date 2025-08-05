-- DDL Script para o Banco de Dados PostgreSQL (Atualizado com User)

-- Excluir tabelas existentes (opcional, para um ambiente de desenvolvimento limpo)
-- A ordem é importante devido às chaves estrangeiras
--DROP TABLE IF EXISTS user_following CASCADE;
--DROP TABLE IF EXISTS artist_album CASCADE;
--DROP TABLE IF EXISTS artist_music CASCADE;
--DROP TABLE IF EXISTS musics CASCADE;
--DROP TABLE IF EXISTS albums CASCADE;
--DROP TABLE IF EXISTS artists CASCADE;
--DROP TABLE IF EXISTS users CASCADE;

---
-- Tabela artists (Sem alterações da revisão anterior)
---
CREATE TABLE artists (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    followers_count INTEGER NOT NULL,
    image_url VARCHAR(255)
);

---
-- Tabela albums (Sem alterações da revisão anterior)
---
CREATE TABLE albums (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image_url VARCHAR(255),
    rate_mean DOUBLE PRECISION,
    genders JSONB,
    about JSONB,
    streammings JSONB
);

---
-- Tabela musics (Sem alterações da revisão anterior)
---
CREATE TABLE musics (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    rate_mean DOUBLE PRECISION,
    image_url VARCHAR(255),
    tags JSONB,
    album_id UUID,

    CONSTRAINT fk_music_album
        FOREIGN KEY (album_id)
        REFERENCES albums (id)
);

---
-- Tabela users (Atualizada: created_at para TIMESTAMP, nome de coluna na join table)
---
CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE, -- Alterado para LocalDateTime em Java -> TIMESTAMP
    image_url VARCHAR(255),
    last_fm VARCHAR(255),
    followers_count INTEGER NOT NULL,
    following_count INTEGER NOT NULL,
    role VARCHAR(255) NOT NULL
);

---
-- Tabela de Junção para artist_album (Artistas e Álbuns - Sem alterações da revisão anterior)
---
CREATE TABLE artist_album (
    album_id UUID NOT NULL,
    artist_id UUID NOT NULL,
    PRIMARY KEY (album_id, artist_id),

    CONSTRAINT fk_artist_album_album
        FOREIGN KEY (album_id)
        REFERENCES albums (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_artist_album_artist
        FOREIGN KEY (artist_id)
        REFERENCES artists (id)
        ON DELETE CASCADE
);

---
-- Tabela de Junção para artist_music (Artistas e Músicas - Sem alterações da revisão anterior)
---
CREATE TABLE artist_music (
    music_id UUID NOT NULL,
    artist_id UUID NOT NULL,
    PRIMARY KEY (music_id, artist_id),

    CONSTRAINT fk_artist_music_music
        FOREIGN KEY (music_id)
        REFERENCES musics (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_artist_music_artist
        FOREIGN KEY (artist_id)
        REFERENCES artists (id)
        ON DELETE CASCADE
);

---
-- Tabela de Junção para user_following (Auto Relacionamento de Usuários - Atualizada a coluna 'follows')
---
CREATE TABLE user_following (
    user_id UUID NOT NULL,          -- ID do Usuário que está seguindo
    followed_user_id UUID NOT NULL, -- ID do Usuário que está sendo seguido (Nome da coluna corrigido)
    PRIMARY KEY (user_id, followed_user_id),

    CONSTRAINT fk_user_following_user_id
        FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_user_following_followed_user_id
        FOREIGN KEY (followed_user_id) -- Chave estrangeira referenciando o ID do usuário seguido
        REFERENCES users (id)
        ON DELETE CASCADE
);