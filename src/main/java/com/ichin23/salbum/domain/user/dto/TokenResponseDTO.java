package com.ichin23.salbum.domain.user.dto;

public record TokenResponseDTO(
        String access_token,
        String token_type,
        String refresh_token,
        UserDTO user
) {

}
