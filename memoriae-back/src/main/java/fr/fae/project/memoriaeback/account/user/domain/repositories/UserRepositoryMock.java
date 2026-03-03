package fr.fae.project.memoriaeback.account.user.domain.repositories;

import fr.fae.project.memoriaeback.account.user.domain.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryMock implements UserRepositoryInter {

    private final List<User> users = new ArrayList<>(List.of(
            new User(UUID.randomUUID(), "Alice", "alice@memoriae.com", "password"),
            new User(UUID.randomUUID(), "Bob", "bob@memoriae.com", "password"),
            new User(UUID.randomUUID(), "Charlie", "charlie@memoriae.com", "password")
    ));

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return users
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void delete(UUID id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
