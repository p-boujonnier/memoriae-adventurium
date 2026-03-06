package fr.fae.project.memoriaeback.account.user.domain.repositories;

import fr.fae.project.memoriaeback.account.user.domain.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {

    // CRUD operations
    Optional<User> findById(UUID id);
    Optional<User> findByPseudo(String pseudo);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    User save(User user);
    void deleteById(UUID id);

    // Utils
    boolean existsById(UUID id);
    boolean existsByPseudo(String pseudo);
    boolean existsByEmail(String email);
}
