package com.ichin23.salbum.domain.refreshToken;

import com.ichin23.salbum.domain.user.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "refresh_tokens")
@Entity
public class RefreshToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "token_value")
    private String tokenValue;
    private Instant expiry_date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public RefreshToken(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getToken_value() {
        return tokenValue;
    }

    public void setToken_value(String token_value) {
        this.tokenValue = token_value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Instant expiry_date) {
        this.expiry_date = expiry_date;
    }
}
