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
    public User save(User user) {
        if (user.getId() == null){
            user.setId(UUID.randomUUID());
        }
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> update(UUID id, User user) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .map(u -> {
                    u.setId(id);
                    u.setPseudo(user.getPseudo());
                    u.setEmail(user.getEmail());
                    u.setPassword(user.getPassword());
                    return u;
                });
    }

    @Override
    public void delete(UUID id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
