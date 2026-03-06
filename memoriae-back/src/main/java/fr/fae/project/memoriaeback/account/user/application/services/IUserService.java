package fr.fae.project.memoriaeback.account.user.application.services;

import fr.fae.project.memoriaeback.common.ServiceResponse;
import fr.fae.project.memoriaeback.account.user.domain.models.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    ServiceResponse<User> findById(UUID id);

    ServiceResponse<List<User>> findAll();

    ServiceResponse<User> create(User user);

    ServiceResponse<User> update(User user);

    ServiceResponse<Void> delete(UUID id);
}
