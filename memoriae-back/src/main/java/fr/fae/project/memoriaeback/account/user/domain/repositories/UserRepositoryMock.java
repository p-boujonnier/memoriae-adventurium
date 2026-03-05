package fr.fae.project.memoriaeback.account.user.domain.repositories;

import fr.fae.project.memoriaeback.account.user.domain.models.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile("mock")
@Repository
public class UserRepositoryMock implements IUserRepository {

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
    public User save(User user) {
        if (user.getId() != null && existsById(user.getId())) {
            users.replaceAll(u -> u.getId().equals(user.getId()) ? user : u);
            return user;
        }
        user.setId(UUID.randomUUID());
        users.add(user);
        return user;
    }

    @Override
    public void deleteById(UUID id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public boolean existsById(UUID id) {
        return users.stream().anyMatch(user -> user.getId().equals(id));
    }
}
