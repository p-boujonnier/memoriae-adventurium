package fr.fae.project.memoriaeback.account.user.api.controllers;

import fr.fae.project.memoriaeback.account.user.api.dtos.requests.UserCreateRequest;
import fr.fae.project.memoriaeback.account.user.api.dtos.requests.UserUpdateRequest;
import fr.fae.project.memoriaeback.account.user.api.dtos.responses.UserPublicResponse;
import fr.fae.project.memoriaeback.account.user.api.mappers.UserMapper;
import fr.fae.project.memoriaeback.account.user.application.common.ServiceResponse;
import fr.fae.project.memoriaeback.account.user.application.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    // Injected dependencies
    private final IUserService IUserService;
    private final UserMapper mapper;

    // Constructors
    public UserController(IUserService IUserService, UserMapper mapper) {
        this.IUserService = IUserService;
        this.mapper = mapper;
    }

    // Endpoints
    /**
     * GET /api/users/{id}
     * Retrieves a user by their unique identifier.
     *
     * @param uuid the unique identifier (UUID) of the user to retrieve
     * @return {@link ResponseEntity} containing a {@link ServiceResponse} with the found user,
     *         or an error message if no user matches the provided UUID
     */
    @GetMapping("/{uuid}")
    public ResponseEntity<ServiceResponse<UserPublicResponse>> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(
                IUserService.findById(uuid).map(mapper::toUserResponse)
        );
    }

    /**
     * GET /api/users
     * Retrieves a list of all users.
     *
     * @return {@link ResponseEntity} containing a {@link ServiceResponse} with the list of users
     */
    @GetMapping
    public ResponseEntity<ServiceResponse<List<UserPublicResponse>>> findAll() {
        return ResponseEntity.ok(
                IUserService
                        .findAll()
                        .map(users -> users
                                .stream()
                                .map(mapper::toUserResponse)
                                .toList())
        );
    }

    /**
     * POST /api/users
     * Creates a new user.
     *
     * @param userCreateRequest the user data to be created
     * @return {@link ResponseEntity} containing a {@link ServiceResponse} with the created user,
     */
    @PostMapping
    public ResponseEntity<ServiceResponse<UserPublicResponse>> create(
            @Valid @RequestBody UserCreateRequest userCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                IUserService
                        .create(mapper.toUserFromCreate(userCreateRequest))
                        .map(mapper::toUserResponse)
        );
    }

    /**
     * PUT /api/users
     * Updates an existing user.
     *
     * @param userUpdateRequest the user data to be updated
     * @return {@link ResponseEntity} containing a {@link ServiceResponse} with the updated user,
     */
    @PutMapping
    public ResponseEntity<ServiceResponse<UserPublicResponse>> update(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return ResponseEntity.ok(
                IUserService.update(mapper.toUserFromUpdate(userUpdateRequest)).map(mapper::toUserResponse)
        );
    }

    /**
     * DELETE /api/users/{uuid}
     * Deletes a user by their unique identifier.
     *
     * @param uuid the UUID of the user to be deleted
     * @return {@link ResponseEntity} containing a {@link ServiceResponse} with HTTP status 200,
     *         or an error message if no user matches the provided UUID
     */
    @DeleteMapping("/{uuid}")
    public ResponseEntity<ServiceResponse<Void>> delete(@PathVariable UUID uuid) {
        return ResponseEntity.ok(IUserService.delete(uuid));
    }
}


