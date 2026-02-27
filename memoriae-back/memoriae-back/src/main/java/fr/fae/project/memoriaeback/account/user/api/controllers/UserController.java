package fr.fae.project.memoriaeback.account.user.api.controllers;

import fr.fae.project.memoriaeback.account.user.domain.models.User;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private List<User> users = new ArrayList<>(List.of(
            new User(UUID.randomUUID(), "Alice", "alice@memoriae.com", "password"),
            new User(UUID.randomUUID(), "Bob", "bob@memoriae.com", "password"),
            new User(UUID.randomUUID(), "Charlie", "charlie@memoriae.com", "password")
    ));
}
