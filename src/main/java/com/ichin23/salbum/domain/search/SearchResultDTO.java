package com.ichin23.salbum.domain.search;

import java.io.Serializable;
import java.util.UUID;

public class SearchResultDTO implements Serializable {
    private UUID id;
    private String name;
    private String subtitle;
    private String type;

    public SearchResultDTO(UUID id, String name, String type, String subtitle){
        this.id=id;
        this.name=name;
        this.subtitle=subtitle;
        this.type=type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
