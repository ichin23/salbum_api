ALTER TABLE musics
DROP CONSTRAINT IF EXISTS fk_music_album;

ALTER TABLE musics
DROP COLUMN IF EXISTS album_id;

CREATE TABLE music_album (
    music_id UUID NOT NULL,
    album_id UUID NOT NULL,
    PRIMARY KEY (music_id, album_id),

    CONSTRAINT fk_music_album_music
        FOREIGN KEY (music_id)
        REFERENCES musics (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_music_album_album
        FOREIGN KEY (album_id)
        REFERENCES albums (id)
        ON DELETE CASCADE
);

ALTER TABLE musics
ADD COLUMN duration INTEGER;

ALTER TABLE musics
ADD COLUMN rank_on_album INTEGER;