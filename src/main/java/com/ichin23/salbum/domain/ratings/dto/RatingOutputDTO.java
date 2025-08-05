package com.ichin23.salbum.domain.ratings.dto;

import com.ichin23.salbum.domain.album.dto.AlbumDTO;
import com.ichin23.salbum.domain.ratings.Ratings;
import com.ichin23.salbum.domain.user.dto.UserDTO;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RatingOutputDTO implements Serializable {

    private Double rate;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private UserDTO user;
    private AlbumDTO album;

    public RatingOutputDTO(Ratings rating){
        this.rate=rating.getRate();
        this.created_at=rating.getCreated_at();
        this.updated_at=rating.getUpdated_at();
        this.user=new UserDTO(rating.getUser());
        this.album=new AlbumDTO(rating.getAlbum());
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public AlbumDTO getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDTO album) {
        this.album = album;
    }
}
