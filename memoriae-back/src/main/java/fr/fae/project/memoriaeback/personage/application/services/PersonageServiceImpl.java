package fr.fae.project.memoriaeback.personage.application.services;

import fr.fae.project.memoriaeback.common.ServiceResponse;
import fr.fae.project.memoriaeback.personage.domain.models.Personage;
import fr.fae.project.memoriaeback.personage.domain.repositories.IPersonageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonageServiceImpl implements IPersonageService {

    private final IPersonageRepository personageRepository;

    public PersonageServiceImpl(List<Personage> personages, IPersonageRepository personageRepository) {
        this.personageRepository = personageRepository;
    }

    @Override
    public ServiceResponse<Personage> findById(UUID uuid) {
        return personageRepository.findById(uuid)
                .map(personage -> new ServiceResponse<>("200", "Personage retrieved successfully", personage))
                .orElse(new ServiceResponse<>("404", "Personage not found", null));
    }

    @Override
    public ServiceResponse<List<Personage>> findAll() {
        return new ServiceResponse<>("200", "List of personages retrieved successfully", personageRepository.findAll());
    }

    @Override
    public ServiceResponse<Personage> create(Personage personage) {
        if (personageRepository.existsById(personage.getId())) {
            return new ServiceResponse<>("400", "Personage already exists", null);
        }
        personage.setId(UUID.randomUUID());
        Personage created = personageRepository.save(personage);
        return new ServiceResponse<>("201", "Personage created successfully", created);
    }

    @Override
    public ServiceResponse<Personage> update(Personage personage) {
        // TODO vérification

        return new ServiceResponse<>("200", "Personage updated successfully", personage);
    }

    @Override
    public ServiceResponse<Void> delete(UUID uuid) {
        try {
            personageRepository.deleteById(uuid);
            return new ServiceResponse<>("200", "Personage deleted successfully", null);
        } catch (Exception e) {
            return new ServiceResponse<>("404", "Personage not found", null);
        }
    }

    @Override
    public boolean existsById(UUID uuid) {
        return personageRepository.existsById(uuid);
    }
}
