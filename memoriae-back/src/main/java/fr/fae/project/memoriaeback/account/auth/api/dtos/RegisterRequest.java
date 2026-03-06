package fr.fae.project.memoriaeback.account.auth.api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    // Attributes
    @NotBlank(message = "Missing required field")
    @Size(min = 3, message = "Invalid username format")
    private String pseudo;

    @NotBlank(message = "Missing required field")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Missing required field")
    @Size(min = 8, message = "Password too short")
    private String password;

    // Constructors
    public RegisterRequest() {
    }
    public RegisterRequest(String pseudo, String email, String password) {
        this.setPseudo(pseudo);
        this.setEmail(email);
        this.setPassword(password);
    }

    // Getters & Setters
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
