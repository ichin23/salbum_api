package com.ichin23.salbum.domain.lastfm;

public class LastFMGetAlbumInfoRequest extends LastFMRequest{
    public String artist;
    public String album;

    public LastFMGetAlbumInfoRequest(
            String artist,
            String album,
            String api_key
    ){
        this.method="album.getinfo";
        this.album=album;
        this.artist=artist;
        this.api_key=api_key;
        this.format="json";
    }

    @Override
    public String toUrlParams() {
        return "?method="+this.method+"&api_key="+this.api_key+"&artist="+this.artist+"&album="+this.album+"&format="+this.format;
    }
}
