package com.ichin23.salbum.domain.spotify;

import java.io.Serializable;

public class SearchResponse implements Serializable {

    ArtistsResponse artists;

    public ArtistsResponse getArtists() {
        return artists;
    }

    public void setArtists(ArtistsResponse artists) {
        this.artists = artists;
    }
}
