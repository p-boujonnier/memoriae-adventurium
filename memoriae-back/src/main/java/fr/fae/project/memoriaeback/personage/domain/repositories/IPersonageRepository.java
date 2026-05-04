package fr.fae.project.memoriaeback.personage.domain.repositories;

import fr.fae.project.memoriaeback.personage.domain.models.Personage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPersonageRepository {

    // CRUD operations
    Optional<Personage> findById(UUID uuid);
    List<Personage> findAll();
    Personage save(Personage personage);
    void deleteById(UUID uuid);

    // Utils
    boolean existsById(UUID id);
}
