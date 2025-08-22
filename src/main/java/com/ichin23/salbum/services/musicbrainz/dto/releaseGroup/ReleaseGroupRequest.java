package com.ichin23.salbum.services.musicbrainz.dto.releaseGroup;

public record ReleaseGroupRequest(
        String query,
        Integer limit,
        String fmt
) {
    public ReleaseGroupRequest(String query){
        this(query, 3, "json");
    }

    public String toUrlParams(){
        return "?query="+this.query+"&limit="+this.limit+"&fmt="+this.fmt;
    }
}
