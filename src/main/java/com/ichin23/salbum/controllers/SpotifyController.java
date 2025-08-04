package com.ichin23.salbum.controllers;

import com.ichin23.salbum.services.spotify.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("spotify")
public class SpotifyController {

    @Autowired
    SpotifyService spotifyService;


    @GetMapping("/search")
    public ResponseEntity<?> searchArtist(
            @RequestParam(value = "q") String q
    ){
        var result = spotifyService.searchArtist(q);

        return ResponseEntity.ok(result);
    }
}
