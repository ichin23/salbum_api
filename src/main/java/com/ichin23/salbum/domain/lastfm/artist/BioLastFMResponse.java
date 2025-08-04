package com.ichin23.salbum.domain.lastfm.artist;

public class BioLastFMResponse {
    private String published;
    private String summary;

    BioLastFMResponse(){}

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
