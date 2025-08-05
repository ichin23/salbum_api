package com.ichin23.salbum.domain.artist.dto;

import com.ichin23.salbum.domain.artist.Artist;

import java.io.Serializable;

public class ArtistAlbumOutputDTO implements Serializable {

    private String id;
    private String name;
    private String mbid;
    private String image_url;

    public ArtistAlbumOutputDTO(Artist artist){
        this.id = artist.getId().toString();
        this.name=artist.getName();
        this.mbid=artist.getMbid();
        this.image_url=artist.getImage_url();
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

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
