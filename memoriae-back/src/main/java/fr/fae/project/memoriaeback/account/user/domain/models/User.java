package fr.fae.project.memoriaeback.account.user.domain.models;

import java.util.UUID;

public class User {
    private UUID id;
    private String pseudo;
    private String email;
    private String password;

    public User() {
    }

    public User(UUID id, String pseudo, String email, String password) {
        this.setId(id);
        this.setPseudo(pseudo);
        this.setEmail(email);
        this.setPassword(password);
    }

    public void setId(UUID id) {}
    public UUID getId() {
        return id;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public String getPseudo() {
        return pseudo;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}
