package fr.fae.project.memoriaeback.personage.domain.repositories;

import fr.fae.project.memoriaeback.personage.domain.models.Personage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonageJpaRepository extends JpaRepository<Personage, UUID> {

}
