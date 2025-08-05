package com.ichin23.salbum.domain.lastfm;

public class LastFMFindTopAlbumsRequest extends LastFMRequest {
    private String artist;
    private String format;

    public LastFMFindTopAlbumsRequest(
            String artist,
            String api_key
    ){
        this.method="artist.gettopalbums";
        this.artist=artist.replace(" ", "+");
        this.api_key=api_key;
        this.format="json";
    }

    @Override
    public String toUrlParams(){
        return "?method="+this.method+"&artist="+this.artist+"&limit=10"+"&api_key="+this.api_key+"&format="+this.format;
    }
}
