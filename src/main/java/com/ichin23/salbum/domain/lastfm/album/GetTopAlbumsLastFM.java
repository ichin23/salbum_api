package com.ichin23.salbum.domain.lastfm.album;

import java.io.Serializable;

public class GetTopAlbumsLastFM implements Serializable {
    TopAlbumsListLastFM topalbums;

    public GetTopAlbumsLastFM(){}

    public TopAlbumsListLastFM getTopalbums() {
        return topalbums;
    }

    public void setTopalbums(TopAlbumsListLastFM topalbums) {
        this.topalbums = topalbums;
    }
}
