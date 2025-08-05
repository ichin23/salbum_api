package com.ichin23.salbum.services;

import com.ichin23.salbum.domain.refreshToken.RefreshToken;
import com.ichin23.salbum.domain.user.User;
import com.ichin23.salbum.repositories.RefreshTokenRepository;
import com.ichin23.salbum.services.exceptions.TokenRefreshException;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    TokenService tokenService;

    //Expires in 7 days
    private final long refreshTokenExpirationMs = 1000 * 60 * 60 * 24 * 7;

    public String createRefreshToken(User user) {
        String tokenValue = UUID.randomUUID().toString();
        Instant expiryDate = Instant.now().plusMillis(refreshTokenExpirationMs);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken_value(tokenValue);
        refreshToken.setUser(user);
        refreshToken.setExpiry_date(expiryDate);

        refreshTokenRepository.save(refreshToken);

        return tokenValue;
    }

    public String validateAndGenerateNewAccessToken(String refreshTokenValue) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByTokenValue(refreshTokenValue);

        if (optionalRefreshToken.isEmpty()) {
            throw new TokenRefreshException("Refresh token not found!");
        }

        RefreshToken refreshToken = optionalRefreshToken.get();

        if (refreshToken.getExpiry_date().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new TokenRefreshException("Refresh token expired!");
        }

        User user = refreshToken.getUser();
        String newAccessToken = tokenService.generateToken(user);

        refreshTokenRepository.delete(refreshToken);
        createRefreshToken(user);

        return newAccessToken;
    }

    public void deleteByUserId(UUID userId){
        refreshTokenRepository.deleteByUser_id(userId);
    }
}
