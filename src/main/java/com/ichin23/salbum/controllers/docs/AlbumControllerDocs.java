package com.ichin23.salbum.controllers.docs;

import com.ichin23.salbum.domain.album.dto.AlbumOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface AlbumControllerDocs {

    @Operation(
            description = "Get Album by ID",
            tags = {"Album"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema =@Schema(implementation = AlbumOutputDTO.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<?> getAlbum(@PathVariable("id")UUID id);


    @Operation(
            description = "Search Albums",
            tags = {"Album"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = AlbumOutputDTO.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "not found",
                            responseCode = "404",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = String.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<?> searchAlbum(@RequestParam(name = "q") String q);
}
