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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
                personageService.findById(uuid).map(mapper::toPersonageResponse)
        );
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<PersonageResponse>> create(
            @Valid @RequestBody PersonageCreateRequest personageRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                personageService.create(mapper.toPersonageFromCreate(personageRequest))
                        .map(mapper::toPersonageResponse)
        );
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<PersonageResponse>> update(
            @PathVariable UUID uuid,
            @Valid @RequestBody PersonageUpdateRequest personageRequest,
            Authentication authentication
    ) {
        if (personageService.existsById(uuid));
        mapper.toPersonageFromUpdate(personageRequest, personageService.findById(uuid).getData());
        return ResponseEntity.ok(
                personageService.update(personageService.findById(uuid).getData()).map(mapper::toPersonageResponse)
        );
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<Void>> delete(@PathVariable UUID uuid) {
        return ResponseEntity.ok(personageService.delete(uuid));
    }
}
