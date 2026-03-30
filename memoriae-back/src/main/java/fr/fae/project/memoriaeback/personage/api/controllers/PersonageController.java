package fr.fae.project.memoriaeback.personage.api.controllers;

import fr.fae.project.memoriaeback.account.user.domain.models.User;
import fr.fae.project.memoriaeback.common.ServiceResponse;
import fr.fae.project.memoriaeback.personage.api.dto.responses.PersonageResponse;
import fr.fae.project.memoriaeback.personage.api.mappers.PersonageMapper;
import fr.fae.project.memoriaeback.personage.domain.models.Personage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personages")
public class PersonageController {

    private final List<Personage> personages;
    private final PersonageMapper mapper;

    public PersonageController(PersonageMapper mapper) {
        this.mapper = mapper;
        this.personages = List.of(
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000001"), "Frodon", "Sacquet", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000002"), "Samsagace", "Gamegie", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000003"), "Meriadoc", "Brandebouc", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000004"), "Peregrin", "Touque", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000005"), "Gandalf", "le Gris", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000006"), "Aragorn", "fils d'Arathorn", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000007"), "Legolas", "Vertefeuille", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000008"), "Gimli", "fils de Glóin", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000009"), "Boromir", "fils de Denethor", new User())
        );
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<List<Personage>>> getAllPersonages() {
        return ResponseEntity.ok(
                new ServiceResponse<>("200", "List of personages retrieved successfully", personages)
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ServiceResponse<PersonageResponse>> getPersonageById(@PathVariable UUID uuid) {
        return personages.stream()
                .filter(personage -> personage.getId().equals(uuid))
                .findFirst()
                .map(personage -> ResponseEntity.ok(
                        new ServiceResponse<>("200", "Personage retrieved successfully", mapper.toResponse(personage))))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ServiceResponse<>("404", "Personage not found", null)));
    }
}
