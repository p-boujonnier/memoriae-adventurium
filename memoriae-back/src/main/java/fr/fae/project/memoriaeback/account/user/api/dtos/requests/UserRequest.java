package fr.fae.project.memoriaeback.account.user.api.dtos.requests;

public class UserRequest {
    private String pseudo;
    private String email;
    private String password;

    public UserRequest() {
    }

    public UserRequest(String pseudo, String email, String password) {
        this.setPseudo(pseudo);
        this.setEmail(email);
        this.setPassword(password);
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
