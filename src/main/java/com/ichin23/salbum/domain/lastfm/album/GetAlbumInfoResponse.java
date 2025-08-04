package com.ichin23.salbum.domain.lastfm.album;


import java.io.Serializable;

public class GetAlbumInfoResponse implements Serializable {

    AlbumWithTracksResponseLastFM album;

    public GetAlbumInfoResponse(){}

    public AlbumWithTracksResponseLastFM getAlbum() {
        return album;
    }

    public void setAlbum(AlbumWithTracksResponseLastFM album) {
        this.album = album;
    }
}
