package com.ichin23.salbum.repositories;

import com.ichin23.salbum.domain.album.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, String> {
}
