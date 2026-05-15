package fr.fae.project.memoriaeback.personage.domain.repositories;

import fr.fae.project.memoriaeback.account.user.domain.models.User;
import fr.fae.project.memoriaeback.personage.domain.models.Personage;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile("mock")
@Repository
public class PersonageRepositoryMock implements IPersonageRepository {

    // Attributes
    private final List<Personage> personages = new ArrayList<>(List.of(
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


    @Override
    public Optional<Personage> findById(UUID uuid) {
        return  personages
                .stream()
                .filter(p -> p.getId().equals(uuid))
                .findFirst();
    }

    @Override
    public List<Personage> findAll() {
        return personages;
    }

    @Override
    public Personage save(Personage personage) {
        if (personage.getId() == null || !existsById(personage.getId())) {
            personage.setId(UUID.randomUUID());
            personages.add(personage);
        } else {
            personages.replaceAll(p -> p.getId().equals(personage.getId()) ? personage : p);
        }
        return personage;
    }

    @Override
    public void deleteById(UUID uuid) {
        personages.removeIf(personage -> personage.getId().equals(uuid));
    }

    // Utility methods
    @Override
    public boolean existsById(UUID uuid) {
        return personages.stream().anyMatch(p -> p.getId().equals(uuid));
    }
}
