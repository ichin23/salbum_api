package com.ichin23.salbum.domain.lastfm.album;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ichin23.salbum.converters.TrackDeserializer;
import com.ichin23.salbum.domain.lastfm.track.TrackResponseLastFM;

import java.io.Serializable;
import java.util.List;

public class TracksOnAlbumLastFM implements Serializable {
    @JsonDeserialize(using = TrackDeserializer.class)
    List<TrackResponseLastFM> track;

    public TracksOnAlbumLastFM(){}

    public List<TrackResponseLastFM> getTrack() {
        return track;
    }

    public void setTrack(List<TrackResponseLastFM> track) {
        this.track = track;
    }
}
