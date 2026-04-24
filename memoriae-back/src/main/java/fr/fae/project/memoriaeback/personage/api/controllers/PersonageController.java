package fr.fae.project.memoriaeback.personage.api.controllers;

import fr.fae.project.memoriaeback.common.ServiceResponse;
import fr.fae.project.memoriaeback.personage.api.dto.requests.PersonageCreateRequest;
import fr.fae.project.memoriaeback.personage.api.dto.requests.PersonageUpdateRequest;
import fr.fae.project.memoriaeback.personage.api.dto.responses.PersonageResponse;
import fr.fae.project.memoriaeback.personage.api.mappers.PersonageMapper;
import fr.fae.project.memoriaeback.personage.application.services.IPersonageService;
import fr.fae.project.memoriaeback.personage.domain.models.Personage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personages")
public class PersonageController {
    private final IPersonageService personageService;
    private final PersonageMapper mapper;

    public PersonageController(IPersonageService personageService, PersonageMapper mapper) {
        this.personageService = personageService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<List<Personage>>> getAllPersonages() {
        return ResponseEntity.ok(personageService.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ServiceResponse<PersonageResponse>> getPersonageById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(
                IPersonageService.findById(uuid).map(mapper::toPersonageResponse)
        );
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<PersonageResponse>> create(
            @Valid @RequestBody PersonageCreateRequest personageRequest
    ) {
        Personage created = mapper.toPersonageFromCreate(personageRequest);
        personages.add(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ServiceResponse<>("201", "Personage created successfully", mapper.toResponse(created))
        );
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<PersonageResponse>> update(
            @PathVariable UUID uuid,
            @Valid @RequestBody PersonageUpdateRequest personageRequest
    ) {
        Personage existing = personages.stream()
                .filter(p -> p.getId().equals(uuid))
                .findFirst()
                .orElse(null);

        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ServiceResponse<>("404", "Personage not found", null));
        }

        mapper.toPersonageFromUpdate(personageRequest, existing);
        personages.replaceAll(p -> p.getId().equals(uuid) ? existing : p);

        return ResponseEntity.ok(
                new ServiceResponse<>("200", "Personage updated successfully", mapper.toResponse(existing))
        );
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<Void>> delete(@PathVariable UUID uuid) {
        personages.removeIf(p -> p.getId().equals(uuid));
        return ResponseEntity.ok(new ServiceResponse<>("200", "Personage deleted successfully", null));
    }
}
