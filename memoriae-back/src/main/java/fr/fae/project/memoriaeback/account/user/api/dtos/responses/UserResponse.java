package fr.fae.project.memoriaeback.account.user.api.dtos.responses;

import java.util.UUID;

public class UserResponse {

    // Attributes
    private UUID id;
    private String pseudo;
    private String email;

    // Constructors
    public UserResponse() {
    }
    public UserResponse(UUID id, String pseudo, String email) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
    }

    // Getters & Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
