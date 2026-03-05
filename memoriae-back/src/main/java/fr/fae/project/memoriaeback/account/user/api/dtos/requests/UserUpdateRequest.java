package fr.fae.project.memoriaeback.account.user.api.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class UserUpdateRequest {

    // Attributes
    private UUID id; // TODO: remove when auth is in place

    @NotBlank(message = "Missing required field")
    @Size(min = 3, message = "Invalid username format")
    private String pseudo;

    @NotBlank(message = "Missing required field")
    @Email(message = "Invalid email format")
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