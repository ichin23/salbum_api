package com.ichin23.salbum.controllers.docs;

import com.ichin23.salbum.domain.ratings.dto.RatingOutputDTO;
import com.ichin23.salbum.domain.search.SearchResultDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface SearchControllerDocs {
    @Operation(
            description = "Search on all database",
            tags = {"Search"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = SearchResultDTO.class))
                                    )
                            }
                    )
            }
    )
    ResponseEntity<?> search(@RequestParam("q") String query);
}
