package com.ichin23.salbum.domain.lastfm.track;

import java.io.Serializable;

public class AttrTrackResponseLastFM implements Serializable {
    Integer rank;

    AttrTrackResponseLastFM(){}

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
