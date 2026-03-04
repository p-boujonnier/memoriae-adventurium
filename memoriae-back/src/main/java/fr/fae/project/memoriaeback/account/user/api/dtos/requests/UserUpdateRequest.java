package fr.fae.project.memoriaeback.account.user.api.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class UserUpdateRequest {

    // Attributes
    private UUID id; // TODO: remove when auth is in place

    @NotBlank(message = "2204:Missing required field")
    @Size(min = 3, message = "2203:Invalid username format")
    private String pseudo;

    @NotBlank(message = "2204:Missing required field")
    @Email(message = "2200:Invalid email format")
    private String email; // TODO: separate endpoint with confirmation flow

    // Constructors
    public UserUpdateRequest() {
    }
    public UserUpdateRequest(UUID id, String pseudo, String email) {
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