package fr.fae.project.memoriaeback.account.user.application.services;

import fr.fae.project.memoriaeback.common.ServiceResponse;
import fr.fae.project.memoriaeback.account.user.domain.models.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    // CRUD
    ServiceResponse<User> findById(UUID id);
    ServiceResponse<User> findByPseudo(String pseudo);
    ServiceResponse<User> findByEmail(String email);
    ServiceResponse<List<User>> findAll();
    ServiceResponse<User> create(User user);
    ServiceResponse<User> update(User user);
    ServiceResponse<Void> delete(UUID id);

    // Utils
    boolean existsByPseudo(String pseudo);
    boolean existsByEmail(String email);
    boolean existsByPseudoAndIdNot(String pseudo, UUID id);
    boolean existsByEmailAndIdNot(String email, UUID id);
}
