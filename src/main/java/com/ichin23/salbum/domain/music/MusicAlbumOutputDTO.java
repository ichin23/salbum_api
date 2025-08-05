package com.ichin23.salbum.domain.music;

import java.io.Serializable;
import java.util.UUID;

public class MusicAlbumOutputDTO implements Serializable {

    private String id;
    private String name;
    private Integer duration;
    private Integer rank_on_album;

    public MusicAlbumOutputDTO(Music music){
        this.id = music.getId().toString();
        this.name=music.getName();
        this.duration=music.getDuration();
        this.rank_on_album=music.getRank_on_album();
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getRank_on_album() {
        return rank_on_album;
    }

    public void setRank_on_album(Integer rank_on_album) {
        this.rank_on_album = rank_on_album;
    }
}
