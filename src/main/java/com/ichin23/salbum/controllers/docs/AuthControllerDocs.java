package com.ichin23.salbum.controllers.docs;

import com.ichin23.salbum.domain.refreshToken.dto.RefreshTokenInput;
import com.ichin23.salbum.domain.refreshToken.dto.RefreshTokenOutput;
import com.ichin23.salbum.domain.user.dto.TokenResponseDTO;
import com.ichin23.salbum.domain.user.dto.UserDTO;
import com.ichin23.salbum.domain.user.dto.UserLoginDTO;
import com.ichin23.salbum.domain.user.dto.UserRegisterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthControllerDocs {

    @Operation(
            description = "Generate new token",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema =@Schema(implementation = RefreshTokenOutput.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<?> refreshToken(@RequestBody RefreshTokenInput data);

    @Operation(
            description = "Create user on database",
            tags = {"Auth"},
            responses = {
                @ApiResponse(
                    description = "success",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema =@Schema(implementation = UserDTO.class)
                            )
                    }
                )
            }
    )
    ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO data);

    @Operation(
            description = "Login User",
            tags = {"Auth"},
            responses = {
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
    ResponseEntity<?> loginUser(@RequestBody UserLoginDTO data);


}
