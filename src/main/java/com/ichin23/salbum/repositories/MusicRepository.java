package com.ichin23.salbum.repositories;

import com.ichin23.salbum.domain.music.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, String> {
}
