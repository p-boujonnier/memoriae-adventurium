package fr.fae.project.memoriaeback.personage.application.services;

import fr.fae.project.memoriaeback.account.user.domain.models.User;
import fr.fae.project.memoriaeback.common.ServiceResponse;
import fr.fae.project.memoriaeback.personage.domain.models.Personage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PersonageService implements IPersonageService {
    private final List<Personage> personages;

    public PersonageService(List<Personage> personages) {
        this.personages = new ArrayList<>(List.of(
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000001"), "Frodon", "Sacquet", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000002"), "Samsagace", "Gamegie", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000003"), "Meriadoc", "Brandebouc", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000004"), "Peregrin", "Touque", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000005"), "Gandalf", "le Gris", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000006"), "Aragorn", "fils d'Arathorn", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000007"), "Legolas", "Vertefeuille", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000008"), "Gimli", "fils de Glóin", new User()),
                new Personage(UUID.fromString("a0000000-0000-0000-0000-000000000009"), "Boromir", "fils de Denethor", new User())
        ));
    }

    @Override
    public ServiceResponse<Personage> findById(UUID id) {
        return personages.stream()
                .filter(personage -> personage.getId().equals(id))
                .findFirst()
                .map(personage -> new ServiceResponse<>("200", "Personage retrieved successfully", personage))
                .orElse(new ServiceResponse<>("404", "Personage not found", null));
    }

    @Override
    public ServiceResponse<List<Personage>> findAll() {
        return new ServiceResponse<>("200", "List of personages retrieved successfully", personages);
    }

    @Override
    public ServiceResponse<Personage> create(Personage personage) {
        return null;
    }

    @Override
    public ServiceResponse<Personage> update(Personage personage) {
        return null;
    }

    @Override
    public ServiceResponse<Void> delete(UUID id) {
        return null;
    }
}
