package fr.fae.project.memoriaeback.account.user.api.dtos.responses;

import fr.fae.project.memoriaeback.account.user.domain.models.enums.Role;

import java.util.List;
import java.util.UUID;

public class UserPublicResponse {

    // Attributes
    private UUID id;
    private String pseudo;
    private String email;
    private List<Role> roles;

    // Constructors
    public UserPublicResponse() {
    }
    public UserPublicResponse(UUID id, String pseudo, String email, List<Role> roles) {
        this.setId(id);
        this.setPseudo(pseudo);
        this.setEmail(email);
        this.setRoles(roles);
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

    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
