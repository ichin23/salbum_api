package com.ichin23.salbum.controllers.docs;

import com.ichin23.salbum.domain.ratings.dto.RatingInputDTO;
import com.ichin23.salbum.domain.ratings.dto.RatingOutputDTO;
import com.ichin23.salbum.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface RatingsControllerDocs {

    @Operation(
            description = "Create a rating for a album",
            tags = {"Ratings"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema =@Schema(implementation = RatingOutputDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "not found",
                            responseCode = "404",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema =@Schema(implementation = String.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<?> createRating(@RequestBody RatingInputDTO data, @AuthenticationPrincipal User currentUser);

    @Operation(
            description = "Update a rating for a album",
            tags = {"Ratings"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema =@Schema(implementation = RatingOutputDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "not found",
                            responseCode = "404",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema =@Schema(implementation = String.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<?> updateRating(@RequestBody RatingInputDTO data, @AuthenticationPrincipal User currentUser);

    @Operation(
            description = "Get ratings by album ID",
            tags = {"Ratings"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = RatingOutputDTO.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "not found",
                            responseCode = "404",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema =@Schema(implementation = String.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<?> getRatingsByAlbum(@PathVariable("id") UUID id);

    @Operation(
            description = "Get ratings by user ID",
            tags = {"Ratings"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = RatingOutputDTO.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "not found",
                            responseCode = "404",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema =@Schema(implementation = String.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<?> getRatingsByUser(@PathVariable("id") UUID id);
}
