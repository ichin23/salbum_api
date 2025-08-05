package com.ichin23.salbum.domain.lastfm.artist;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ichin23.salbum.converters.TagDeserializer;
import com.ichin23.salbum.converters.TagsListDeserializer;

import java.io.Serializable;
import java.util.List;

public class TagsListLastFMResponse implements Serializable {
    @JsonDeserialize(using = TagDeserializer.class)
    private List<TagLastFMResponse> tag;

    public TagsListLastFMResponse(){}

    public List<TagLastFMResponse> getTag() {
        return tag;
    }

    public void setTag(List<TagLastFMResponse> tag) {
        this.tag = tag;
    }
}
