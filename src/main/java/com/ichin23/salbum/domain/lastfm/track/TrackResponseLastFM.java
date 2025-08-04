package com.ichin23.salbum.domain.lastfm.track;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ichin23.salbum.domain.lastfm.artist.ArtistLastFMResponse;
import com.ichin23.salbum.domain.lastfm.artist.SimplesArtistLastFMResponse;

import java.io.Serializable;

public class TrackResponseLastFM implements Serializable {
    private String name;
    private Integer duration;
    @JsonProperty("@attr")
    private AttrTrackResponseLastFM attr;
    private SimplesArtistLastFMResponse artist;

    public TrackResponseLastFM(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public AttrTrackResponseLastFM getAttr() {
        return attr;
    }

    public void setAttr(AttrTrackResponseLastFM attr) {
        this.attr = attr;
    }

    public SimplesArtistLastFMResponse getArtist() {
        return artist;
    }

    public void setArtist(SimplesArtistLastFMResponse artist) {
        this.artist = artist;
    }
}
