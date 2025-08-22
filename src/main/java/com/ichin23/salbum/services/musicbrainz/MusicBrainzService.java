package com.ichin23.salbum.services.musicbrainz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ichin23.salbum.domain.DownloadRequestDTO;
import com.ichin23.salbum.producers.DownloadImagesProducer;
import com.ichin23.salbum.services.musicbrainz.dto.ReleaseGroupDTO;
import com.ichin23.salbum.services.musicbrainz.dto.release.ReleaseDTO;
import com.ichin23.salbum.services.musicbrainz.dto.releaseGroup.ReleaseGroup;
import com.ichin23.salbum.services.musicbrainz.dto.releaseGroup.ReleaseGroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


@Service
public class MusicBrainzService {

    @Value("${musicbrainz.api_url}")
    String apiUrl;

    @Autowired
    private ReleaseImagesService releaseImagesService;

    @Autowired
    private DownloadImagesProducer downloadImagesProducer;

    private final RestTemplate restTemplate;

    public MusicBrainzService(RestTemplateBuilder restTemplateBuilder){this.restTemplate=restTemplateBuilder.build();}

    public ReleaseGroupDTO searchReleaseGroup(String query){
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "salbum-api/0.1");

        ReleaseGroupRequest requestParams = new ReleaseGroupRequest(query);

        String url = apiUrl+="/release-group"+requestParams.toUrlParams();

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<ReleaseGroupDTO> response = this.restTemplate.getForEntity(url, ReleaseGroupDTO.class);
        ReleaseGroupDTO body = response.getBody();

        assert body != null;
        getImages(body.releaseGroups());

        return response.getBody();
    }

    @Async
    private void getImages(List<ReleaseGroup> releases){
        for (ReleaseGroup item: releases){
            if(!releaseImagesService.imageExist(item.releases().getFirst().id())){
                try{
                    downloadImagesProducer.request(new DownloadRequestDTO(item.releases().getFirst().id()));
                } catch (JsonProcessingException e) {
                    System.out.println("Erro ao requisitar download");
                }
            }
        }
    }

    public ReleaseDTO searchRelease(String id){

        String url = apiUrl+"/release/"+id+"?inc=url-rels+artists+recordings&fmt=json";
        ResponseEntity<ReleaseDTO> response = this.restTemplate.getForEntity(url, ReleaseDTO.class);

        ReleaseDTO body = response.getBody();
        String image = releaseImagesService.pathImage(response.getBody().id());

        if(image.equals("loading")){
            try {
                downloadImagesProducer.request(new DownloadRequestDTO(body.id()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        body = body.withImage(image);

        return body;
    }
}
