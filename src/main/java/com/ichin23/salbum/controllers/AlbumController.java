package com.ichin23.salbum.controllers;

import com.ichin23.salbum.domain.album.dto.AlbumOutputDTO;
import com.ichin23.salbum.services.AlbumService;
import com.ichin23.salbum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("album")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlbum(
            @PathVariable("id") UUID id
    ){
        try{
            var result = albumService.findAlbumById(id);
            return ResponseEntity.ok(result.stream().map(AlbumOutputDTO::new));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> searchAlbum(
            @RequestParam(name = "q") String q
    ){
        var result = albumService.searchAlbum(q);
        return ResponseEntity.ok(result.stream().map(AlbumOutputDTO::new));
    }
}
