package com.ichin23.salbum.repositories;

import com.ichin23.salbum.domain.artist.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistsRepository extends JpaRepository<Artist, UUID> {
}
