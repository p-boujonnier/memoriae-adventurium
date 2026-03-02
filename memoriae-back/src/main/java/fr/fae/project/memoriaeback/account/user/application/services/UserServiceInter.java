package fr.fae.project.memoriaeback.account.user.application.services;

import fr.fae.project.memoriaeback.account.user.domain.models.User;

import java.util.List;

public interface UserServiceInter {

    public User findById(String id);

    public List<User> findAll();

    public User save(User user);

    public User update(String id, User user);

    public void delete(String id);
}
