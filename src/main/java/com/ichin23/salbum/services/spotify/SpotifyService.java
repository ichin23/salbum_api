package com.ichin23.salbum.services.spotify;

import com.ichin23.salbum.domain.spotify.SearchResponse;
import com.ichin23.salbum.domain.spotify.SpotifySearchRequest;
import com.ichin23.salbum.domain.spotify.SpotifyTokenRequest;
import com.ichin23.salbum.domain.spotify.SpotifyTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyService {

    @Value("${spotify.client_secret}")
    private String client_secret;

    @Value("${spotify.client_id}")
    private String client_id;

    @Value("${spotify.api_url}")
    String apiUrl;

    private final RestTemplate restTemplate;

    public String access_token;

    public SpotifyService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public void getCredentials(){
        String url = "https://accounts.spotify.com/api/token";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        SpotifyTokenRequest clients = new SpotifyTokenRequest(
            client_id,
            client_secret,
            "client_credentials"
        );

        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(clients.toObject(), headers);

        ResponseEntity<SpotifyTokenResponse> response = this.restTemplate.postForEntity(url, entity, SpotifyTokenResponse.class, 1);

        if(response.getStatusCode()== HttpStatus.OK){
            this.access_token = response.getBody().getAccess_token();
        }
    }

    public SearchResponse searchArtist(String name){
        if (access_token==null){
            getCredentials();
        }


        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(access_token);

        SpotifySearchRequest requestParams = new SpotifySearchRequest(
                name,
                "artist",
                "BR",
                3
        );
        String url = apiUrl+"/search"+requestParams.toUrlParams();

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<SearchResponse> response = this.restTemplate.exchange(url, HttpMethod.GET, request, SearchResponse.class, 1);

        return response.getBody();
    }
}
