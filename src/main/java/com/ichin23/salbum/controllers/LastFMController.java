package com.ichin23.salbum.controllers;

import com.ichin23.salbum.services.lastfm.LastFMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lastfm")
public class LastFMController {
    @Autowired
    LastFMService lastFMService;

    @GetMapping("/search")
    public ResponseEntity<?> getArtist(
            @RequestParam(value = "q") String q
    ){
        var result = lastFMService.searchArtist(q);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/searchAlbum")
    public ResponseEntity<?> getAlbum(
            @RequestParam(value = "q") String q
    ){
        var result = lastFMService.searchTopAlbums(q);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/albumInfo")
    public ResponseEntity<?> getAlbumInfo(
            @RequestParam(value = "artist") String artist,
            @RequestParam(value = "album") String album
    ){
        System.out.println(artist);
        var result = lastFMService.getAlbumInfo(album, artist);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/fetchArtist")
    public ResponseEntity<?> fetchArtist(
            @RequestParam(value = "artist") String artist
    ){
        System.out.println(artist);
        lastFMService.fetchFullData( artist);
        return ResponseEntity.ok("ok");
    }
}
