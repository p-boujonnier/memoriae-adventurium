package fr.fae.project.memoriaeback.account.user.api.dtos.responses;

import java.util.UUID;

public class UserPublicResponse {

    // Attributes
    private UUID id;
    private String pseudo;
    private String email;

    // Constructors
    public UserPublicResponse() {
    }
    public UserPublicResponse(UUID id, String pseudo, String email) {
        this.setId(id);
        this.setPseudo(pseudo);
        this.setEmail(email);
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
