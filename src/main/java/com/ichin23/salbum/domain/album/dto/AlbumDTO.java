package com.ichin23.salbum.domain.album.dto;

import com.ichin23.salbum.domain.album.Album;
import com.ichin23.salbum.domain.artist.dto.ArtistDTO;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

public class AlbumDTO implements Serializable {
    private String id;
    private String name;
    private Set<ArtistDTO> artists;
    private String image_url;

    public AlbumDTO(Album album){
        this.id=album.getId().toString();
        this.name=album.getName();
        this.artists = album.getArtists().stream().map(ArtistDTO::new).collect(Collectors.toSet());
        this.image_url=album.getImage_url();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(Set<ArtistDTO> artists) {
        this.artists = artists;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
