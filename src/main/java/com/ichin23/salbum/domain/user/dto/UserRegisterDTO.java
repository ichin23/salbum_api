package com.ichin23.salbum.domain.user.dto;

public record UserRegisterDTO(
        String email,
        String name,
        String username,
        String password
) {
}
