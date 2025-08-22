package com.ichin23.salbum.controllers.docs;

import com.ichin23.salbum.domain.lastfm.album.GetTopAlbumsLastFM;
import com.ichin23.salbum.domain.lastfm.artist.GetArtistResponse;
import com.ichin23.salbum.domain.refreshToken.dto.RefreshTokenOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface LastFMControllerDocs {

    @Operation(
            description = "Search Artist on LastFM",
            tags = {"LastFM"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema =@Schema(implementation = GetArtistResponse.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<?> getArtist(@RequestParam(value = "q") String q);

    @Operation(
            description = "Search Albums on LastFM",
            tags = {"LastFM"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema =@Schema(implementation = GetTopAlbumsLastFM.class)
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<?> getAlbum(@RequestParam(value = "q") String q);

    @Operation(
            description = "Fetch Artist on LastFM",
            tags = {"LastFM"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema =@Schema(implementation = String.class)
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<?> fetchArtist(@RequestParam(value = "artist") String artist);
}
