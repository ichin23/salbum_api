package com.ichin23.salbum.services;

import com.ichin23.salbum.domain.album.Album;
import com.ichin23.salbum.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    public Optional<Album> findAlbumById(UUID id){
        return albumRepository.findById(id);
    }

    public Set<Album> searchAlbum(String q){
        return albumRepository.searchByName(q);
    }


}
