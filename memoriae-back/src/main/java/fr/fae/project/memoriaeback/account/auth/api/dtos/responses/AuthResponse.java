package fr.fae.project.memoriaeback.account.auth.api.dtos.responses;

import java.util.UUID;

public class AuthResponse {
    // Attributs
    private String accessToken;
    private String refreshToken;
    private UUID userId;
    private String pseudo;
    private long expiresIn;

    // Constructors with refreshToken (login)
    public AuthResponse(String accessToken, String refreshToken, UUID userId, String pseudo, long expiresIn) {
        this.setAccessToken(accessToken);
        this.setRefreshToken(refreshToken);
        this.setUserId(userId);
        this.setPseudo(pseudo);
        this.setExpiresIn(expiresIn);
    }

    // Constructors without refreshToken (refresh)
    public AuthResponse(String accessToken, UUID userId, String pseudo, long expiresIn) {
        this(accessToken, null, userId, pseudo, expiresIn);
    }

    // Getters & Setters
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public long getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}