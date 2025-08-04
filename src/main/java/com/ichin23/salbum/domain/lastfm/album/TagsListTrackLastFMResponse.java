package com.ichin23.salbum.domain.lastfm.album;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ichin23.salbum.converters.TagsListDeserializer;
import com.ichin23.salbum.domain.lastfm.artist.TagLastFMResponse;

import java.io.Serializable;
import java.util.List;

public class TagsListTrackLastFMResponse implements Serializable {
    @JsonDeserialize(using= TagsListDeserializer.class)
    private List<TagLastFMResponse> tag;

    public TagsListTrackLastFMResponse(){}

    public List<TagLastFMResponse> getTag() {
        return tag;
    }

    public void setTag(List<TagLastFMResponse> tag) {
        this.tag = tag;
    }
}
