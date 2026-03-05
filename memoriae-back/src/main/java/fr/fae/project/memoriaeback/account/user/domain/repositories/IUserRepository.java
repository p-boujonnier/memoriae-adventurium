package fr.fae.project.memoriaeback.account.user.domain.repositories;

import fr.fae.project.memoriaeback.account.user.domain.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {

    Optional<User> findById(UUID id);

    List<User> findAll();

    User save(User user);

    void deleteById(UUID id);

    boolean existsById(UUID id);
}
