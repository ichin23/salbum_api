package com.ichin23.salbum.domain.album;

import com.ichin23.salbum.converters.json.MapJsonConverter;
import com.ichin23.salbum.converters.json.StringSetConverter;
import com.ichin23.salbum.domain.artist.Artist;
import com.ichin23.salbum.domain.music.Music;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.*;

@Table(name = "albums")
@Entity
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;


    private String image_url;
    private Double rate_mean;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Set<String> genders = new HashSet<String>();

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> about = new HashMap<>();

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> streammings = new HashMap<String, Object>();

    @ManyToMany
    @JoinTable(
            name = "artist_album",
            joinColumns = @JoinColumn(name = "album_id", columnDefinition = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "artist_id", columnDefinition = "uuid")
    )
    private Set<Artist> artists = new HashSet<Artist>();

    @ManyToMany(mappedBy = "albums", cascade = CascadeType.ALL) // "albums" deve corresponder ao nome da propriedade Set na classe Music
    private Set<Music> musics = new HashSet<>(); // Nome da coleção no Album

    public void addArtist(Artist artist){
        this.artists.add(artist);
        artist.getAlbums().add(this);
    }

    public void removeArtist(Artist artist){
        this.artists.remove(artist);
        artist.getAlbums().remove(this);
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

    public Map<String, Object> getAbout() {
        return about;
    }

    public void setAbout(Map<String, Object> about) {
        this.about = about;
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

    public Map<String, Object> getStreammings() {
        return streammings;
    }

    public void setStreammings(Map<String, Object> streammings) {
        this.streammings = streammings;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<Music> getMusics() {
        return musics;
    }

    public void setMusics(Set<Music> musics) {
        this.musics = musics;
    }
}
