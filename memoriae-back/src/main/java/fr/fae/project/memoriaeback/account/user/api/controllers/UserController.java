package fr.fae.project.memoriaeback.account.user.api.controllers;

import fr.fae.project.memoriaeback.account.user.domain.models.User;
import fr.fae.project.memoriaeback.account.user.domain.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * GET /api/users
     * @return List of users
     */
    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * GET /api/users/{id}
     * @param id User ID
     * @return User with specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id) {
        return userRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/users
     * @param user User to create
     * @return Created user
     */
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(201).body(savedUser);
    }

    /**
     * PUT /api/users/{id}
     * @param id User ID
     * @param user Updated user
     * @return Updated user
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable UUID id, @RequestBody User user) {
        return userRepository.update(id, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/users/{id}
     * @param id User ID
     * @return Deleted user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable UUID id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(id);
                    return ResponseEntity.ok(user);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}


