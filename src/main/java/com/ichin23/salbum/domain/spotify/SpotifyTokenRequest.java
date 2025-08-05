package com.ichin23.salbum.domain.spotify;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;

public class SpotifyTokenRequest implements Serializable {
    private String grant_type;
    private String client_id;
    private String client_secret;

    public SpotifyTokenRequest(String client_id, String client_secret, String grant_type){
        this.client_id=client_id;
        this.client_secret=client_secret;
        this.grant_type=grant_type;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public MultiValueMap<String,String> toObject(){
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", this.getGrant_type());
        body.add("client_id", this.getClient_id());
        body.add("client_secret", this.getClient_secret());
        return body;
    }
}
