package fr.fae.project.memoriaeback.account.user.application.services;

import fr.fae.project.memoriaeback.account.user.domain.models.User;

import java.util.List;
import java.util.UUID;

public interface UserServiceInter {

    User findById(UUID id);

    List<User> findAll();

    User save(User user);

    void delete(UUID id);
}
