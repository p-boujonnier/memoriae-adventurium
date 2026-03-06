package fr.fae.project.memoriaeback.account.user.application.services;

import fr.fae.project.memoriaeback.common.ServiceResponse;
import fr.fae.project.memoriaeback.account.user.domain.models.User;
import fr.fae.project.memoriaeback.account.user.domain.repositories.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ServiceResponse<User> findById(UUID id) {
        if (id == null) {
            return new ServiceResponse<>("2100","User not found",null);
        }
        return new ServiceResponse<>("2001","User retrieved successfully",userRepository.findById(id).get());
    }

    @Override
    public ServiceResponse<List<User>> findAll() {
        return new ServiceResponse<>("2001","Users retrieved successfully",userRepository.findAll());
    }

    @Override
    public ServiceResponse<User> create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User created = userRepository.save(user);
        return new ServiceResponse<>("2000", "User created successfully", created);
    }

    @Override
    public ServiceResponse<User> update(User user) {
        return userRepository.findById(user.getId())
                .map(_ -> {
                    User updated = userRepository.save(user);
                    return new ServiceResponse<>("2002", "User updated successfully", updated);
                })
                .orElse(new ServiceResponse<>("2100", "User not found", null));
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
