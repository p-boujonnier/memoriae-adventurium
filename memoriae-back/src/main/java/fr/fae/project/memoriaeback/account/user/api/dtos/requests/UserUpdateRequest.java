package fr.fae.project.memoriaeback.account.user.api.dtos.requests;

import java.util.UUID;

public class UserUpdateRequest {

    private UUID id; // TODO: remove when auth is in place
    private String pseudo;
    private String email; // TODO: separate endpoint with confirmation flow

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(UUID id, String pseudo, String email) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
    }

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