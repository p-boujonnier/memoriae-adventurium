package fr.fae.project.memoriaeback.personage.application.services;

import fr.fae.project.memoriaeback.common.ServiceResponse;
import fr.fae.project.memoriaeback.personage.domain.models.Personage;

import java.util.List;
import java.util.UUID;

public interface IPersonageService {

    // CRUD
    ServiceResponse<Personage> findById(UUID id);
    ServiceResponse<List<Personage>> findAll();
    ServiceResponse<Personage> create(Personage personage);
    ServiceResponse<Personage> update(Personage personage);
    ServiceResponse<Void> delete(UUID id);

    // Utils
    boolean existsById(UUID uuid);
}
