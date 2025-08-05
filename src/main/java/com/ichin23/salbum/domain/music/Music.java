package com.ichin23.salbum.domain.music;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ichin23.salbum.converters.json.StringSetConverter;
import com.ichin23.salbum.domain.album.Album;
import com.ichin23.salbum.domain.artist.Artist;
import com.ichin23.salbum.domain.lastfm.album.AlbumResponseLastFM;
import com.ichin23.salbum.domain.lastfm.album.TracksOnAlbumLastFM;
import com.ichin23.salbum.domain.lastfm.track.TrackResponseLastFM;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "musics")
@Entity
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;
    private Double rate_mean;
    private String image_url;
    private Integer duration;
    private Integer rank_on_album;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Set<String> tags = new HashSet<String>();

    @ManyToMany
    @JoinTable(
            name = "music_album", // Nome da tabela de junção (pode ser "album_music" também)
            joinColumns = @JoinColumn(name = "music_id", columnDefinition = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "album_id", columnDefinition = "uuid")
    )
    private Set<Album> albums= new HashSet<>();

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name="artist_music",
            joinColumns = @JoinColumn(name="music_id", columnDefinition = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "artist_id", columnDefinition = "uuid") //ID do artista
    )
    private Set<Artist> artists = new HashSet<Artist>();

    public Music(){}



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

    public Double getRate_mean() {
        return rate_mean;
    }

    public void setRate_mean(Double rate_mean) {
        this.rate_mean = rate_mean;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<Album> getAlbum() {
        return albums;
    }

    public void setAlbum(Set<Album> album) {
        this.albums = album;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getRank_on_album() {
        return rank_on_album;
    }

    public void setRank_on_album(Integer rank_on_album) {
        this.rank_on_album = rank_on_album;
    }

    public void addAlbum(Album album){
        this.albums.add(album);
        album.getMusics().add(this);
    }

    public void removeAlbum(Album album){
        this.albums.remove(album);
        album.getMusics().remove(this);
    }


    public void addArtist(Artist artist){
        this.artists.add(artist);
        artist.getMusics().add(this);
    }

    public void removeArtist(Artist artist){
        this.albums.remove(artist);
        artist.getMusics().remove(this);
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artist) {
        this.artists = artist;
    }
}
