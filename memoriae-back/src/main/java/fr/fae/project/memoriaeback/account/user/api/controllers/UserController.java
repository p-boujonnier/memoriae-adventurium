package fr.fae.project.memoriaeback.account.user.api.controllers;

import fr.fae.project.memoriaeback.account.user.application.services.UserServiceInter;
import fr.fae.project.memoriaeback.account.user.domain.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserServiceInter userServiceInter;

    public UserController(UserServiceInter userServiceInter) {
        this.userServiceInter = userServiceInter;
    }

    /**
     * GET /api/users/{id}
     * @param uuid User ID
     * @return User with specified ID
     */
    @GetMapping("/{uuid}")
    public ResponseEntity<User> findById(@PathVariable UUID uuid) {
        User user = userServiceInter.findById(uuid);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * GET /api/users
     * @return List of users
     */
    @GetMapping
    public List<User> findAll() {
        return userServiceInter.findAll();
    }

    /**
     * POST /api/users
     * @param user User to create
     * @return Created user
     */
    @PostMapping
    public ResponseEntity<User> save(
            @RequestBody User user) {
        User savedUser = userServiceInter.save(user);
        return ResponseEntity.status(201).body(savedUser);
    }

    /**
     * PUT /api/users/{id}
     * @param uuid User ID
     * @param user Updated user
     * @return Updated user
     */
    @PutMapping("/{uuid}")
    public ResponseEntity<User> update(
            @PathVariable UUID uuid,
            @RequestBody User user) {
        User updatedUser = userServiceInter.update(uuid, user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * DELETE /api/users/{id}
     * @param uuid User ID
     * @return Deleted user
     */
    @DeleteMapping("/{uuid}")
    public ResponseEntity<User> delete(
            @PathVariable UUID uuid) {
        if (userServiceInter.findById(uuid) == null) {
            return ResponseEntity.notFound().build();
        }
        userServiceInter.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}


