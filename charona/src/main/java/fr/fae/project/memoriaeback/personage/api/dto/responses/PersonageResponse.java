package fr.fae.project.memoriaeback.personage.api.dto.responses;

import java.util.UUID;

public class PersonageResponse {

    // Attributes
    private UUID id;
    public String firstName ;
    public String lastName ;

    // Constructors
    public PersonageResponse() {
    }
    public PersonageResponse(UUID id, String firstName, String lastName) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

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
}