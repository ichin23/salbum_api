package com.ichin23.salbum.domain.spotify;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ArtistResponse implements Serializable {
    Map<String, Object> external_urls;
    List<String> genres;
    String href;
    String id;
    String name;
    Integer popularity;

    public Map<String, Object> getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(Map<String, Object> external_urls) {
        this.external_urls = external_urls;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }
}
