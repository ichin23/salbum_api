package com.ichin23.salbum.services;

import com.ichin23.salbum.domain.album.Album;
import com.ichin23.salbum.domain.ratings.Ratings;
import com.ichin23.salbum.domain.ratings.RatingsId;
import com.ichin23.salbum.domain.user.User;
import com.ichin23.salbum.repositories.RatingsRepository;
import com.ichin23.salbum.repositories.UserRepository;
import com.ichin23.salbum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class RatingsService {

    @Autowired
    UserService userService;

    @Autowired
    AlbumService albumService;

    @Autowired
    RatingsRepository ratingsRepository;

    public Ratings createRating(UUID userId, UUID albumId, Double rate){
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Album album = albumService.findAlbumById(albumId)
                .orElseThrow(() -> new ResourceNotFoundException("Album not found"));

        RatingsId ids = new RatingsId();
        ids.setUser_id(userId);
        ids.setAlbum_id(albumId);

        Ratings rating = new Ratings();
        rating.setId(ids);
        rating.setRate(rate);
        rating.setUser(user);
        rating.setAlbum(album);

        return ratingsRepository.save(rating);
    }

    public Ratings updateRating(UUID userId, UUID albumId, Double rate){
        RatingsId ids = new RatingsId();
        ids.setUser_id(userId);
        ids.setAlbum_id(albumId);

        Ratings rating = ratingsRepository.findById(ids).orElseThrow(()->new ResourceNotFoundException("Rating not found"));

        rating.setRate(rate);

        return ratingsRepository.save(rating);
    }

    public Set<Ratings> getRatingsByAlbum(UUID albumId){
        Album album = albumService.findAlbumById(albumId)
                .orElseThrow(() -> new ResourceNotFoundException("Album not found"));

        return ratingsRepository.findByAlbum_id(albumId);
    }

    public Set<Ratings> getRatingsByUser(UUID userId){
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return ratingsRepository.findByUser_id(userId);
    }
}
