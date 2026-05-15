package fr.fae.project.memoriaeback.personage.api.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PersonageUpdateRequest {

    // Attributes
    @NotBlank( message = "Missing required field")
    @Size(min = 2, max = 50, message = "Invalid first name format")
    public String firstName ;

    @NotBlank( message = "Missing required field")
    @Size(min = 2, max = 50, message = "Invalid last name format")
    public String lastName ;

    // Constructors
    public PersonageUpdateRequest() {
    }
    public PersonageUpdateRequest(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    // Getters & Setters
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
