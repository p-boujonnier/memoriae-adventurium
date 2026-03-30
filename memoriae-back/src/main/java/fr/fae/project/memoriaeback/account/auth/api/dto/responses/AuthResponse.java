package fr.fae.project.memoriaeback.account.auth.api.dto.responses;

import java.util.List;
import java.util.UUID;

public class AuthResponse {
    // Attributs
    private String accessToken;
    private String refreshToken;
    private UUID userId;
    private String pseudo;
    private long expiresIn;
    private List<String> roles;

    // Constructors with refreshToken (login)
    public AuthResponse(String accessToken, String refreshToken, UUID userId, String pseudo, long expiresIn, List<String> roles) {
        this.setAccessToken(accessToken);
        this.setRefreshToken(refreshToken);
        this.setUserId(userId);
        this.setPseudo(pseudo);
        this.setExpiresIn(expiresIn);
        this.setRoles(roles);
    }

    // Constructors without refreshToken (refresh)
    public AuthResponse(String accessToken, UUID userId, String pseudo, long expiresIn, List<String> roles) {
        this(accessToken, null, userId, pseudo, expiresIn, roles);
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

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}