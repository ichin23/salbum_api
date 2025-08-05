package com.ichin23.salbum.domain.album.dto;

import com.ichin23.salbum.domain.album.Album;
import com.ichin23.salbum.domain.artist.dto.ArtistAlbumOutputDTO;
import com.ichin23.salbum.domain.music.MusicAlbumOutputDTO;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AlbumOutputDTO implements Serializable {

    private String id;
    private String name;
    private String image_url;
    private Double rate_mean;
    private Set<String> genders;
    private Map<String, Object> about;
    private Map<String, Object> streammings;
    private Set<ArtistAlbumOutputDTO> artists;
    private Set<MusicAlbumOutputDTO> musics;

    public AlbumOutputDTO(Album album){
        this.id=album.getId().toString();
        this.name=album.getName();
        this.image_url= album.getImage_url();
        this.rate_mean= album.getRate_mean();
        this.genders=album.getGenders();
        this.about=album.getAbout();
        this.streammings=album.getStreammings();
        this.artists=album.getArtists().stream().map(ArtistAlbumOutputDTO::new).collect(Collectors.toSet());
        this.musics=album.getMusics().stream().map(MusicAlbumOutputDTO::new).collect(Collectors.toSet());
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Double getRate_mean() {
        return rate_mean;
    }

    public void setRate_mean(Double rate_mean) {
        this.rate_mean = rate_mean;
    }

    public Set<String> getGenders() {
        return genders;
    }

    public void setGenders(Set<String> genders) {
        this.genders = genders;
    }

    public Map<String, Object> getAbout() {
        return about;
    }

    public void setAbout(Map<String, Object> about) {
        this.about = about;
    }

    public Map<String, Object> getStreammings() {
        return streammings;
    }

    public void setStreammings(Map<String, Object> streammings) {
        this.streammings = streammings;
    }

    public Set<ArtistAlbumOutputDTO> getArtists() {
        return artists;
    }

    public void setArtists(Set<ArtistAlbumOutputDTO> artists) {
        this.artists = artists;
    }

    public Set<MusicAlbumOutputDTO> getMusics() {
        return musics;
    }

    public void setMusics(Set<MusicAlbumOutputDTO> musics) {
        this.musics = musics;
    }
}
