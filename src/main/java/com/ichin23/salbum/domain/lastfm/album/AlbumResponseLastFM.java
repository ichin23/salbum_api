package com.ichin23.salbum.domain.lastfm.album;

import com.ichin23.salbum.domain.lastfm.ImageLastFM;
import com.ichin23.salbum.domain.lastfm.artist.ArtistLastFMResponse;
import com.ichin23.salbum.domain.lastfm.artist.TagLastFMResponse;
import com.ichin23.salbum.domain.lastfm.artist.TagsListLastFMResponse;

import java.io.Serializable;
import java.util.List;

public class AlbumResponseLastFM implements Serializable {
    String name;
    Integer playcount;
    TagsListLastFMResponse tags;
    String mbid;
    String url;
    ArtistLastFMResponse artist;
    List<ImageLastFM> image;

    public AlbumResponseLastFM(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlaycount() {
        return playcount;
    }

    public void setPlaycount(Integer playcount) {
        this.playcount = playcount;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArtistLastFMResponse getArtist() {
        return artist;
    }

    public void setArtist(ArtistLastFMResponse artist) {
        this.artist = artist;
    }

    public List<ImageLastFM> getImage() {
        return image;
    }

    public void setImage(List<ImageLastFM> image) {
        this.image = image;
    }

    public TagsListLastFMResponse getTags() {
        return tags;
    }

    public void setTags(TagsListLastFMResponse tags) {
        this.tags = tags;
    }
}
