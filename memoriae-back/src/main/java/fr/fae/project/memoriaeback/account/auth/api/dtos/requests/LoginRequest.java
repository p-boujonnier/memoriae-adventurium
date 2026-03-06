package fr.fae.project.memoriaeback.account.auth.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {

    // Attributes
    @NotBlank(message = "Missing required field")
    @Size(min = 3, message = "Invalid username format")
    private String identifier;

    @NotBlank(message = "Missing required field")
    @Size(min = 8, message = "Password too short")
    private String password;

    private boolean rememberMe;

    // Constructors
    public LoginRequest() {}
    public LoginRequest(String identifier, String password, boolean rememberMe) {
        this.setIdentifier(identifier);
        this.setPassword(password);
        this.setRememberMe(rememberMe);
    }

    // Getters & Setters
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
