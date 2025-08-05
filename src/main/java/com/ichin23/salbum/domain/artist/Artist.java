package com.ichin23.salbum.domain.artist;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ichin23.salbum.domain.album.Album;
import com.ichin23.salbum.domain.lastfm.artist.ArtistLastFMResponse;
import com.ichin23.salbum.domain.music.Music;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name="artists")
@Entity
public class Artist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String name;
    private String description;
    private String bio_date_published;
    private String mbid;

    @Column(columnDefinition = "INTEGER NOT NULL DEFAULT 0")
    private Integer followers_count;
    private String image_url;

    @JsonBackReference
    @ManyToMany(mappedBy = "artists", cascade = CascadeType.ALL)
    private Set<Album> albums = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "artists", cascade = CascadeType.ALL)
    private Set<Music> musics = new HashSet<>();

    public Artist(){}

    public Artist(ArtistLastFMResponse artistfm){
        this.image_url = artistfm.getImage().stream().filter(imageLastFM -> imageLastFM.getSize().equals("large")).toList().getFirst().getUrl();
        this.name = artistfm.getName();
        this.mbid = artistfm.getMbid();
        this.description = artistfm.getBio().getSummary();
        this.bio_date_published = artistfm.getBio().getPublished();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(Integer followers_count) {
        this.followers_count = followers_count;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Music> getMusics() {
        return musics;
    }

    public void setMusics(Set<Music> musics) {
        this.musics = musics;
    }

    public String getBio_date_published() {
        return bio_date_published;
    }

    public void setBio_date_published(String bio_date_published) {
        this.bio_date_published = bio_date_published;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }
}
