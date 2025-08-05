package com.ichin23.salbum.repositories;

import com.ichin23.salbum.domain.album.Album;
import com.ichin23.salbum.domain.ratings.Ratings;
import com.ichin23.salbum.domain.ratings.RatingsId;
import com.ichin23.salbum.domain.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface RatingsRepository extends JpaRepository<Ratings, RatingsId> {


    Set<Ratings> findByAlbum_id(UUID album_id);
    Set<Ratings> findByUser_id(UUID user_id);
}
