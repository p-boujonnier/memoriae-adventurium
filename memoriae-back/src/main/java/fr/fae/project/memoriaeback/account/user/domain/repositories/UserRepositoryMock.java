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

    // Attributes
    private final List<User> users = new ArrayList<>(List.of(
            new User(UUID.randomUUID(), "Alice", "alice@memoriae.com", "password"),
            new User(UUID.randomUUID(), "Bob", "bob@memoriae.com", "password"),
            new User(UUID.randomUUID(), "Charlie", "charlie@memoriae.com", "password")
    ));

    // CRUD operations
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
    public Optional<User> findByPseudo(String pseudo) {
        return users.stream().filter(u -> u.getPseudo().equals(pseudo)).findFirst();
    }
    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream().filter(u -> u.getEmail().equals(email)).findFirst();
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

    // Utility methods
    public boolean existsById(UUID id) {
        return users.stream().anyMatch(user -> user.getId().equals(id));
    }
    @Override
    public boolean existsByPseudo(String pseudo) {
        return users.stream().anyMatch(user -> user.getPseudo().equals(pseudo));
    }
    @Override
    public boolean existsByEmail(String email) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
    }
    @Override
    public boolean existsByPseudoAndIdNot(String pseudo, UUID id) {
        return users.stream().anyMatch(u -> u.getPseudo().equals(pseudo) && !u.getId().equals(id));
    }
    @Override
    public boolean existsByEmailAndIdNot(String email, UUID id) {
        return users.stream().anyMatch(u -> u.getEmail().equals(email) && !u.getId().equals(id));
    }}
