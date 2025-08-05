package com.ichin23.salbum.repositories;

import com.ichin23.salbum.domain.album.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {

    @Query("SELECT a FROM Album a WHERE a.name LIKE LOWER(CONCAT ('%', :query, '%'))")
    Set<Album> searchByName(@Param("query") String query);

    @Query(value = """
    -- Query para Albuns com o nome do artista como subtítulo
    SELECT
        CAST(a.id AS VARCHAR),
        a.name,
        'album' AS type,
        STRING_AGG(ar.name, ', ') AS subtitle
    FROM
        albums a
    JOIN
        artist_album aa ON a.id = aa.album_id
    JOIN
        artists ar ON aa.artist_id = ar.id
    WHERE
        LOWER(a.name) LIKE LOWER(CONCAT('%', :query, '%'))
    GROUP BY a.id, a.name

    UNION

    -- Query para Músicas com o nome do artista como subtítulo
    SELECT
        CAST(m.id AS VARCHAR),
        m.name,
        'music' AS type,
        STRING_AGG(ar.name, ', ') AS subtitle
    FROM
        musics m
    JOIN
        artist_music am ON m.id = am.music_id
    JOIN
        artists ar ON am.artist_id = ar.id
    WHERE
        LOWER(m.name) LIKE LOWER(CONCAT('%', :query, '%'))
    GROUP BY m.id, m.name

    UNION

    -- Query para Artistas (sem subtítulo)
    SELECT
        CAST(a.id AS VARCHAR),
        a.name,
        'artist' AS type,
        NULL AS subtitle
    FROM
        artists a
    WHERE
        LOWER(a.name) LIKE LOWER(CONCAT('%', :query, '%'))
    """, nativeQuery = true)
    List<Object[]> globalSearch(@Param("query") String query);
}
