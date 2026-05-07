package fr.fae.project.memoriaeback.personage.domain.repositories;

import fr.fae.project.memoriaeback.personage.domain.models.Personage;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile("jpa")
@Repository
public class PersonageRepositoryJpaAdapter implements IPersonageRepository{
    // Injected dependencies
    private final PersonageJpaRepository personageJpaRepository;

    // Constructors
    public PersonageRepositoryJpaAdapter(PersonageJpaRepository personageJpaRepository) {
        this.personageJpaRepository = personageJpaRepository;
    }

    // Methods
    @Override
    public Optional<Personage> findById(UUID id) {
        return personageJpaRepository.findById(id);
    }

    @Override
    public List<Personage> findAll() {
        return personageJpaRepository.findAll();
    }

    @Override
    public Personage save(Personage personage) {
        return personageJpaRepository.save(personage);
    }

    @Override
    public void deleteById(UUID uuid) {
        personageJpaRepository.deleteById(uuid);
    }

    @Override
    public boolean existsById(UUID id) {
        return personageJpaRepository.existsById(id);
    }

}
