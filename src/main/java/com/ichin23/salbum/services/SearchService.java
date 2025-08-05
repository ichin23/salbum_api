package com.ichin23.salbum.services;

import com.ichin23.salbum.domain.search.SearchResultDTO;
import com.ichin23.salbum.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchService {

    @Autowired
    private AlbumRepository albumRepository;

    public List<SearchResultDTO> globalSearch(String q){
        List<Object[]> results = albumRepository.globalSearch(q);

        // Mapeia o resultado para o DTO
        return results.stream()
                .map(row -> new SearchResultDTO(
                        UUID.fromString((String) row[0]), // Converte o ID de volta para UUID
                        (String) row[1],
                        (String) row[2],
                        (String) row[3]
                ))
                .toList();
    }
}
