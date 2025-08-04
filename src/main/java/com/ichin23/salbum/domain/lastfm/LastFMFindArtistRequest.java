package com.ichin23.salbum.domain.lastfm;

public class LastFMFindArtistRequest extends LastFMRequest {
    private String artist;
    private String format;

    public LastFMFindArtistRequest(
            String artist,
            String api_key
    ){
        this.method="artist.getinfo";
        this.artist=artist;
        this.api_key=api_key;
        this.format="json";
    }

    @Override
    public String toUrlParams(){
        return "?method="+this.method+"&artist="+this.artist+"&api_key="+this.api_key+"&format="+this.format;
    }
}
