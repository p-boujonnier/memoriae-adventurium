package fr.fae.project.memoriaeback.account.user.application.services;

import fr.fae.project.memoriaeback.account.user.domain.models.User;
import fr.fae.project.memoriaeback.account.user.domain.repositories.UserRepositoryInter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserServiceInter{

    private final UserRepositoryInter userRepository;

    public UserServiceImpl(UserRepositoryInter userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(UUID id) {
        return userRepository
                .findById(id)
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
    public User update(UUID id, User user) {
        return userRepository
                .update(id, user)
                .orElse(null);
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(id);
    }
}
