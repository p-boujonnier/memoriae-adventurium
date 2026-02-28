package fr.fae.project.memoriaeback.account.user.domain.repositories;

import fr.fae.project.memoriaeback.account.user.domain.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryMock implements UserRepository{

    private List<User> users = new ArrayList<>(List.of(
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
                .filter(user -> user.getId().equals(id))
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
    public void delete(UUID id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
