package fr.fae.project.memoriaeback.account.auth.application.service;

import fr.fae.project.memoriaeback.account.auth.api.dtos.requests.LoginRequest;
import fr.fae.project.memoriaeback.account.auth.api.dtos.requests.RegisterRequest;
import fr.fae.project.memoriaeback.account.auth.api.dtos.responses.AuthResponse;
import fr.fae.project.memoriaeback.account.security.jwt.JwtService;
import fr.fae.project.memoriaeback.account.security.refresh.application.IRefreshTokenService;
import fr.fae.project.memoriaeback.account.security.refresh.domain.models.RefreshToken;
import fr.fae.project.memoriaeback.account.user.application.services.IUserService;
import fr.fae.project.memoriaeback.account.user.domain.models.User;
import fr.fae.project.memoriaeback.common.ServiceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements IAuthService {


    @Value("${jwt.access-token.expiration}")
    private long accessTokenExpiration;

    // Dependencies
    private final IUserService userService;
    private final JwtService jwtService;
    private final IRefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    // Constructors
    public AuthServiceImpl(
            IUserService userService,
            JwtService jwtService,
            IRefreshTokenService refreshTokenService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    // Methods
    /**
     * Register a new user with the provided registration request.
     * @param request The registration request containing user details.
     * @return ServiceResponse containing the result of the registration process.
     */
    @Override
    public ServiceResponse<AuthResponse> register(RegisterRequest request) {
        // Check if pseudo or email already exists
        ServiceResponse<User> created = userService.create(
                new User(request.getPseudo(), request.getEmail(), request.getPassword()));
        if (created.getData() == null) {
            return new ServiceResponse<>(created.getCode(), created.getMessage(), null);
        }
        User savedUser = created.getData();
        String accessToken = jwtService.generateAccessToken(savedUser.getId(), savedUser.getEmail());
        ServiceResponse<String> refreshToken = refreshTokenService
                .createAndPersistRefreshToken(savedUser, null, null, null);
        return new ServiceResponse<>("1000", "Register successful",
                new AuthResponse(
                        accessToken,
                        refreshToken.getData(),
                        savedUser.getId(),
                        savedUser.getPseudo(),
                        accessTokenExpiration));
    }

    /**
     * Login a user and generate authentication tokens.
     * @param request Login request containing credentials.
     * @param device Device information.
     * @param ipAddress IP address of the login attempt.
     * @param userAgent User agent string.
     */
    @Override
    public ServiceResponse<AuthResponse> login(LoginRequest request, String device, String ipAddress, String userAgent) {
        User user;
        if (request.getIdentifier().contains("@")) {
            user = userService.findByEmail(request.getIdentifier()).getData();
        } else {
            user = userService.findByPseudo(request.getIdentifier()).getData();
        }
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new ServiceResponse<>("1100", "Invalid credentials", null);
        }
        String accessToken = jwtService.generateAccessToken(user.getId(), user.getEmail());
        ServiceResponse<String> refreshToken = refreshTokenService
                .createAndPersistRefreshToken(user, device, ipAddress, userAgent);
        return new ServiceResponse<>("1000", "Login successful",
                new AuthResponse(
                        accessToken,
                        refreshToken.getData(),
                        user.getId(),
                        user.getPseudo(),
                        accessTokenExpiration));
    }

    /**
     * Logout a user by invalidating their refresh token.
     * @param rawRefreshToken Raw refresh token to be invalidated.
     */
    @Override
    public ServiceResponse<Void> logout(String rawRefreshToken) {
        return refreshTokenService.revokeRefreshToken(rawRefreshToken);
    }

    /**
     * Refresh authentication tokens using a valid refresh token.
     * @param rawRefreshToken Raw refresh token for token refresh.
     * @param device Device information.
     * @param ipAddress IP address of the refresh attempt.
     * @param userAgent User agent string.
     */
    @Override
    public ServiceResponse<AuthResponse> refresh(
            String rawRefreshToken,
            String device,
            String ipAddress,
            String userAgent) {

        ServiceResponse<String> rotatedToken = refreshTokenService.rotateRefreshToken(
                rawRefreshToken,
                device,
                ipAddress,
                userAgent);

        if (rotatedToken.getData() == null){
            return new ServiceResponse<>(rotatedToken.getCode(), rotatedToken.getMessage(), null);
        }

        ServiceResponse<?> foundToken = refreshTokenService.findValidByToken(rotatedToken.getData());
        if (foundToken.getData() == null){
            return new ServiceResponse<>(foundToken.getCode(), foundToken.getMessage(), null);
        }

        var rt = (RefreshToken) foundToken.getData();
        User user = rt.getUser();

        String accessToken = jwtService.generateAccessToken(user.getId(), user.getEmail());
        String newRawToken = rotatedToken.getData();

        return new ServiceResponse<>("1002", "Token refreshed successfully",
                new AuthResponse(accessToken, newRawToken, user.getId(), user.getPseudo(), accessTokenExpiration)
        );
    }

    @Override
    public ServiceResponse<AuthResponse> me(String userId) {
        ServiceResponse<User> userResponse = userService.findById(UUID.fromString(userId));

        if (userResponse.getData() == null) {
            return new ServiceResponse<>("1101", "Account not found", null);
        }

        User user = userResponse.getData();

        return new ServiceResponse<>("1000", "Authentication successful",
                new AuthResponse(null, null, user.getId(), user.getPseudo(), 0)
        );
    }
}
