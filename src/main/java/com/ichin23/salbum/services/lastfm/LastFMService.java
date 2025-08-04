package com.ichin23.salbum.services.lastfm;

import com.ichin23.salbum.domain.album.Album;
import com.ichin23.salbum.domain.artist.Artist;
import com.ichin23.salbum.domain.lastfm.LastFMFindTopAlbumsRequest;
import com.ichin23.salbum.domain.lastfm.LastFMGetAlbumInfoRequest;
import com.ichin23.salbum.domain.lastfm.album.AlbumResponseLastFM;
import com.ichin23.salbum.domain.lastfm.album.AlbumWithTracksResponseLastFM;
import com.ichin23.salbum.domain.lastfm.album.GetAlbumInfoResponse;
import com.ichin23.salbum.domain.lastfm.album.GetTopAlbumsLastFM;
import com.ichin23.salbum.domain.lastfm.artist.ArtistLastFMResponse;
import com.ichin23.salbum.domain.lastfm.artist.GetArtistResponse;
import com.ichin23.salbum.domain.lastfm.LastFMFindArtistRequest;
import com.ichin23.salbum.domain.lastfm.artist.TagLastFMResponse;
import com.ichin23.salbum.domain.music.Music;
import com.ichin23.salbum.repositories.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LastFMService {
    @Value("${lastfm.client_key}")
    private String client_key;

    @Value("${lastfm.api_url}")
    String apiUrl;

    @Autowired
    ArtistsRepository artistsRepository;

    private final RestTemplate restTemplate;

    public LastFMService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }



    public void fetchFullData(String artistName){
        ArtistLastFMResponse artistLastFM = searchArtist(artistName).getArtist();
        Artist artist = new Artist();

        artist.setName(artistLastFM.getName());
        artist.setDescription(artistLastFM.getBio().getSummary());
        artist.setImage_url(artistLastFM.getImage().stream().filter(imageLastFM -> imageLastFM.getSize().equals("large")).toList().getFirst().getUrl());
        artist.setMbid(artistLastFM.getMbid());
        artist.setBio_date_published(artistLastFM.getBio().getPublished());
        artist.setFollowers_count(0);

        GetTopAlbumsLastFM artistTopAlbums = searchTopAlbums(artist.getName());


        for (AlbumResponseLastFM albumLastFM: artistTopAlbums.getTopalbums().getAlbum()){
            Album album = new Album();
            album.setName(albumLastFM.getName());
            album.addArtist(artist);
            album.setImage_url(albumLastFM.getImage().stream().filter(imageLastFM -> imageLastFM.getSize().equals("large")).toList().getFirst().getUrl());
            System.out.println(album.getName());

            AlbumWithTracksResponseLastFM albumTracks = getAlbumInfo(album.getName(), artist.getName()).getAlbum();

            if(albumTracks.getTracks()==null){
                continue;
            }

            album.setGenders(albumTracks.getTags().getTag().stream().map(TagLastFMResponse::getName).collect(Collectors.toSet()));
            Map<String, Object> about = new HashMap<>();

            if(albumTracks.getWiki()!=null){
                about.put("description", albumTracks.getWiki().getSummary());
                about.put("published", albumTracks.getWiki().getPublished());
                album.setAbout(about);
            }

            Set<String> genders;

            if (albumLastFM.getTags()==null){
                genders = artistLastFM.getTags().getTag().stream().map(TagLastFMResponse::getName).collect(Collectors.toSet());
            }else{
                genders = albumLastFM.getTags().getTag().stream().map(TagLastFMResponse::getName).collect(Collectors.toSet());
            }

            album.setGenders(genders);

            Set<String> finalGenders = genders;
            List<Music> tracks = albumTracks.getTracks().getTrack().stream().map(track->{
                Music music = new Music();
                music.setName(track.getName());
                music.addAlbum(album);
                music.addArtist(artist);
                music.setTags(finalGenders);
                return music;
            }).toList();
        }
        artistsRepository.save(artist);
    }

    public GetArtistResponse searchArtist(String name){

        String params = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("method", "artist.getinfo")
                .queryParam("api_key", client_key)
                .queryParam("artist", name)
                .queryParam("format", "json").toUriString();

        ResponseEntity<GetArtistResponse> response = this.restTemplate.getForEntity(params,GetArtistResponse.class);

        GetArtistResponse artist = response.getBody();
        return artist;
    }

    public GetTopAlbumsLastFM searchTopAlbums(String artistName){
        String params = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("method", "artist.gettopalbums")
                .queryParam("api_key", client_key)
                .queryParam("artist", artistName)
                .queryParam("format", "json").toUriString();
        ResponseEntity<GetTopAlbumsLastFM> response = this.restTemplate.getForEntity(params, GetTopAlbumsLastFM.class);
        GetTopAlbumsLastFM topalbums = response.getBody();
        return topalbums;
    }

    public GetAlbumInfoResponse getAlbumInfo(String albumName, String artistName){
        System.out.println(albumName);
        LastFMGetAlbumInfoRequest request = new LastFMGetAlbumInfoRequest(
                artistName,
                albumName,
                client_key
        );

        String params = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("method", "album.getinfo")
                .queryParam("api_key", client_key)
                .queryParam("artist", artistName)
                .queryParam("album", albumName)
                .queryParam("format", "json").toUriString();

        ResponseEntity<GetAlbumInfoResponse> response = this.restTemplate.getForEntity(apiUrl+request.toUrlParams(), GetAlbumInfoResponse.class);
        GetAlbumInfoResponse albumInfo = response.getBody();
        return albumInfo;
    }
}
