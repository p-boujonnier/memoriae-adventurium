package fr.fae.project.memoriaeback.account.user.application.services;

import fr.fae.project.memoriaeback.account.user.application.common.ServiceResponse;
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
    public ServiceResponse<User> findById(UUID id) {
        if (id == null || userRepository.findById(id).isEmpty()) {
            return new ServiceResponse<>("2100","User not found",null);
        }
        return new ServiceResponse<>("2001","User retrieved successfully",userRepository.findById(id).get());
    }

    @Override
    public ServiceResponse<List<User>> findAll() {
        return new ServiceResponse<>("2001","Users retrieved successfully",userRepository.findAll());
    }

    @Override
    public ServiceResponse<User> save(User user) {
        if (user.getId() != null && userRepository.existsById(user.getId())){
            User updated = userRepository.save(user);
            return new ServiceResponse<>("2002", "User updated successfully", updated);
        }
        User created = userRepository.save(user);
        return new ServiceResponse<>("2000", "User created successfully", created);
    }

    @Override
    public ServiceResponse<Void> delete(UUID id) {
        return userRepository.findById(id)
                .map(_ -> {
                    userRepository.deleteById(id);
                    return new ServiceResponse<Void>("2003", "User deleted successfully", null);
                })
                .orElse(new ServiceResponse<>("2100", "User not found", null));
    }
}
