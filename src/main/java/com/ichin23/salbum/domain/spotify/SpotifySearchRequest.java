package com.ichin23.salbum.domain.spotify;

public class SpotifySearchRequest {
    private String q;
    private String type;
    private String market;
    private Integer limit;

    public SpotifySearchRequest(String q, String type, String market, Integer limit){
        this.q=q;
        this.type=type;
        this.market=market;
        this.limit=limit;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String toUrlParams(){
        return "?q="+this.q+"&type="+this.type+"&market="+this.market+"&limit="+this.limit;
    }
}
