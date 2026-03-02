package fr.fae.project.memoriaeback.account.user.application.services;

import fr.fae.project.memoriaeback.account.user.domain.models.User;
import fr.fae.project.memoriaeback.account.user.domain.repositories.UserRepositoryInter;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserServiceInter{

    private final UserRepositoryInter userRepository;

    public UserServiceImpl(UserRepositoryInter userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(String id) {
        return userRepository
                .findById(UUID.fromString(id))
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(String id, User user) {
        return userRepository
                .update(UUID.fromString(id), user)
                .orElse(null);
    }

    @Override
    public void delete(String id) {
        userRepository.delete(UUID.fromString(id));
    }
}
