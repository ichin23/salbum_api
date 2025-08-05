package com.ichin23.salbum.domain.lastfm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ImageLastFM implements Serializable {
    @JsonProperty("#text")
    private String url;
    private String size;

    public ImageLastFM(String url, String size) {
        this.url = url;
        this.size = size;
    }

    public ImageLastFM() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
