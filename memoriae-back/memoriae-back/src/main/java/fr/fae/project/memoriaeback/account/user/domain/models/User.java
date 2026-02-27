package fr.fae.project.memoriaeback.account.user.domain.models;

import java.util.UUID;

public class User {
    private UUID id;
    private String pseudo;
    private String email;
    private String password;

    public User() {
    }

    public User(UUID id, String pseudo, String password, String email) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
    }

    public UUID getId() {
        return id;
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
