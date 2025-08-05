package com.ichin23.salbum.domain.lastfm.album;

import java.io.Serializable;
import java.util.List;

public class TopAlbumsListLastFM implements Serializable {
    List<AlbumResponseLastFM> album;

    public TopAlbumsListLastFM(){}

    public List<AlbumResponseLastFM> getAlbum() {
        return album;
    }

    public void setAlbum(List<AlbumResponseLastFM> album) {
        this.album = album;
    }
}
