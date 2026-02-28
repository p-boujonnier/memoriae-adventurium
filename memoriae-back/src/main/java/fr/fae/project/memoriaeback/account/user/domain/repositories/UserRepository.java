package fr.fae.project.memoriaeback.account.user.domain.repositories;

import fr.fae.project.memoriaeback.account.user.domain.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(UUID id);

    User save(User user);

    void delete(UUID id);
}
