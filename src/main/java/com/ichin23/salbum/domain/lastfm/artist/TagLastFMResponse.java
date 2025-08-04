package com.ichin23.salbum.domain.lastfm.artist;

import java.io.Serializable;

public class TagLastFMResponse implements Serializable {
    private String name;

    public TagLastFMResponse(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
