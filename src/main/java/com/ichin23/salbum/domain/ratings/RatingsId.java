package com.ichin23.salbum.domain.ratings;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class RatingsId implements Serializable {

    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "album_id")
    private UUID album_id;

    public RatingsId() {
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(UUID album_id) {
        this.album_id = album_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RatingsId ratingsId = (RatingsId) o;
        return Objects.equals(user_id, ratingsId.user_id) && Objects.equals(album_id, ratingsId.album_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, album_id);
    }
}