package com.ichin23.salbum.controllers;

import com.ichin23.salbum.services.AlbumService;
import com.ichin23.salbum.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    SearchService searchService;

    @GetMapping
    public ResponseEntity<?> search(
            @RequestParam("q") String query
    ){
        var result = searchService.globalSearch(query);
        return ResponseEntity.ok(result);
    }
}
