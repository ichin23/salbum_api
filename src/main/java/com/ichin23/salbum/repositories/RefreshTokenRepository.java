package com.ichin23.salbum.repositories;

import com.ichin23.salbum.domain.refreshToken.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findByTokenValue(String token_value);
    void deleteByUser_id(UUID user_id);
}
