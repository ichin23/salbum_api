package com.ichin23.salbum.domain.lastfm.artist;

import com.ichin23.salbum.domain.lastfm.ImageLastFM;

import java.io.Serializable;
import java.util.List;

public class ArtistLastFMResponse implements Serializable {

    private String name;
    private String mbid;
    private String url;
    private List<ImageLastFM> image;

    private TagsListLastFMResponse tags;

    private BioLastFMResponse bio;

    public ArtistLastFMResponse(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ImageLastFM> getImage() {
        return image;
    }

    public void setImage(List<ImageLastFM> image) {
        this.image = image;
    }

    public TagsListLastFMResponse getTags() {
        return tags;
    }

    public void setTags(TagsListLastFMResponse tags) {
        this.tags = tags;
    }

    public BioLastFMResponse getBio() {
        return bio;
    }

    public void setBio(BioLastFMResponse bio) {
        this.bio = bio;
    }
}
