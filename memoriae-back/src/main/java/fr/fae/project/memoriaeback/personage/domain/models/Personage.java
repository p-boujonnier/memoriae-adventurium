package fr.fae.project.memoriaeback.personage.domain.models;

import fr.fae.project.memoriaeback.account.user.domain.models.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "personages")
public class Personage {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    // Constructors
    public Personage() {
    }
    public Personage(String firstName, String lastName, User owner) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setOwner(owner);
    }

    // Getters & Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
}
