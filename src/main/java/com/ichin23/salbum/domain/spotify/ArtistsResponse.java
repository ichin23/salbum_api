package com.ichin23.salbum.domain.spotify;

import java.io.Serializable;
import java.util.List;

public class ArtistsResponse implements Serializable {

    String href;
    Integer limit;
    String next;
    Integer total;
    List<ArtistResponse> items;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<ArtistResponse> getItems() {
        return items;
    }

    public void setItems(List<ArtistResponse> items) {
        this.items = items;
    }
}
