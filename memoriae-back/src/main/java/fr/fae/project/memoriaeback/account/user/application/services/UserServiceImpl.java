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
        return userRepository.findById(id)
                .map(user -> new ServiceResponse<>("2001", "User retrieved successfully", user))
                .orElse(new ServiceResponse<>("2100", "User not found with id : " + id, null));
    }

    @Override
    public ServiceResponse<User> findByPseudo(String pseudo) {
        return userRepository.findByPseudo(pseudo)
                .map(user -> new ServiceResponse<>("2001", "User retrieved successfully", user))
                .orElse(new ServiceResponse<>("2100", "User not found with pseudo : " + pseudo, null));
    }

    @Override
    public ServiceResponse<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> new ServiceResponse<>("2001", "User retrieved successfully", user))
                .orElse(new ServiceResponse<>("2100", "User not found with email : " + email, null));
    }

    @Override
    public ServiceResponse<List<User>> findAll() {
        return new ServiceResponse<>("2001", "Users retrieved successfully", userRepository.findAll());
    }

    @Override
    public ServiceResponse<User> create(User user) {
        if (userRepository.existsByPseudo(user.getPseudo())) {
            return new ServiceResponse<>("2102", "Pseudo already taken", null);
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return new ServiceResponse<>("2101", "Email already in use", null);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User created = userRepository.save(user);
        return new ServiceResponse<>("2000", "User created successfully", created);
    }

    @Override
    public ServiceResponse<User> update(User user) {
        if (userRepository.existsByPseudoAndIdNot(user.getPseudo(), user.getId())) {
            return new ServiceResponse<>("2102", "Pseudo already taken", null);
        }
        if (userRepository.existsByEmailAndIdNot(user.getEmail(), user.getId())) {
            return new ServiceResponse<>("2101", "Email already in use", null);
        }
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

    @Override
    public boolean existsByPseudo(String pseudo) {
        return userRepository.existsByPseudo(pseudo);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPseudoAndIdNot(String pseudo, UUID id) {
        return userRepository.existsByPseudoAndIdNot(pseudo, id);
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, UUID id) {
        return userRepository.existsByEmailAndIdNot(email, id);
    }
}
