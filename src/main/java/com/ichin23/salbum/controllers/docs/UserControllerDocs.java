package com.ichin23.salbum.controllers.docs;

import com.ichin23.salbum.domain.user.dto.TokenResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserControllerDocs {
    @Operation(
        description = "Find usr by Id",
        tags = {"Users"},
        responses= {
            @ApiResponse(
                description = "success",
                responseCode = "200",
                content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema =@Schema(implementation = TokenResponseDTO.class)
                    )
                }
            )
        }
    )
    @GetMapping("/{id}")
    ResponseEntity<?> getUserById(@PathVariable("id") String id);

}
