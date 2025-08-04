package com.ichin23.salbum.domain.lastfm.album;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ichin23.salbum.converters.TagsListDeserializer;
import com.ichin23.salbum.domain.lastfm.ImageLastFM;
import com.ichin23.salbum.domain.lastfm.artist.ArtistLastFMResponse;
import com.ichin23.salbum.domain.lastfm.artist.BioLastFMResponse;
import com.ichin23.salbum.domain.lastfm.artist.TagsListLastFMResponse;

import java.io.Serializable;
import java.util.List;

public class AlbumWithTracksResponseLastFM implements Serializable {
    String artist;
    String mbid;
    @JsonDeserialize(using= TagsListDeserializer.class)
    TagsListLastFMResponse tags;
    String name;
    List<ImageLastFM> image;
    TracksOnAlbumLastFM tracks;
    String playcount;
    String url;
    BioLastFMResponse wiki;

    public AlbumWithTracksResponseLastFM(){}

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public TagsListLastFMResponse getTags() {
        return tags;
    }

    public void setTags(TagsListLastFMResponse tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImageLastFM> getImage() {
        return image;
    }

    public void setImage(List<ImageLastFM> image) {
        this.image = image;
    }

    public TracksOnAlbumLastFM getTracks() {
        return tracks;
    }

    public void setTracks(TracksOnAlbumLastFM tracks) {
        this.tracks = tracks;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BioLastFMResponse getWiki() {
        return wiki;
    }

    public void setWiki(BioLastFMResponse wiki) {
        this.wiki = wiki;
    }
}
