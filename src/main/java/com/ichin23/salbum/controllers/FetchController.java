package com.ichin23.salbum.controllers;

import com.ichin23.salbum.services.musicbrainz.MusicBrainzService;
import com.ichin23.salbum.services.musicbrainz.ReleaseImagesService;
import com.ichin23.salbum.services.musicbrainz.dto.ReleaseGroupDTO;
import com.ichin23.salbum.services.musicbrainz.dto.release.ReleaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("fetch")
public class FetchController {

    @Autowired
    MusicBrainzService musicBrainzService;

    @Autowired
    ReleaseImagesService releaseImagesService;

    @GetMapping
    public ResponseEntity<?> getReleaseGroup(@RequestParam(value="query") String query){
        ReleaseGroupDTO response = musicBrainzService.searchReleaseGroup(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping("release/{releaseId}")
    public ResponseEntity<?> getRelease(
            @PathVariable("releaseId") String id
    ){
        ReleaseDTO response = musicBrainzService.searchRelease(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id){
        try{

            byte[] image = releaseImagesService.getImage(id);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(image, httpHeaders, HttpStatus.OK);
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
