package com.ichin23.salbum.domain.lastfm;

public abstract class LastFMRequest {
    String method;
    String api_key;
    String format;

    public abstract String toUrlParams();
}
