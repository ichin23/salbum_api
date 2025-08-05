package com.ichin23.salbum.domain.lastfm.artist;

import java.io.Serializable;

public class GetArtistResponse implements Serializable {

    private ArtistLastFMResponse artist;

    public GetArtistResponse(){}

    public ArtistLastFMResponse getArtist() {
        return artist;
    }

    public void setArtist(ArtistLastFMResponse artist) {
        this.artist = artist;
    }
}
