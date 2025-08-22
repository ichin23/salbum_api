package com.ichin23.salbum.controllers;

import com.ichin23.salbum.controllers.docs.RatingsControllerDocs;
import com.ichin23.salbum.domain.ratings.dto.RatingInputDTO;
import com.ichin23.salbum.domain.ratings.dto.RatingOutputDTO;
import com.ichin23.salbum.domain.user.User;
import com.ichin23.salbum.repositories.RatingsRepository;
import com.ichin23.salbum.services.RatingsService;
import com.ichin23.salbum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("ratings")
public class RatingsController implements RatingsControllerDocs {

    @Autowired
    RatingsService ratingsService;

    @Override
    @PostMapping("/album")
    public ResponseEntity<?> createRating(@RequestBody RatingInputDTO data, @AuthenticationPrincipal User currentUser){
        try{
            var result = ratingsService.createRating(currentUser.getId(), data.album_id(), data.rate());

            return ResponseEntity.ok(new RatingOutputDTO(result));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/album")
    public ResponseEntity<?> updateRating(@RequestBody RatingInputDTO data, @AuthenticationPrincipal User currentUser){
        try{
            var result = ratingsService.updateRating(currentUser.getId(), data.album_id(), data.rate());
            return ResponseEntity.ok(new RatingOutputDTO(result));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<?> getRatingsByAlbum(
            @PathVariable("id") UUID id
    ){
        try{
            var result = ratingsService.getRatingsByAlbum(id);
            return ResponseEntity.ok(result.stream().map(RatingOutputDTO::new).collect(Collectors.toSet()));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getRatingsByUser(
            @PathVariable("id") UUID id
    ){
        try{
            var result = ratingsService.getRatingsByUser(id);
            return ResponseEntity.ok(result.stream().map(RatingOutputDTO::new).collect(Collectors.toSet()));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
}
