package fr.fae.project.memoriaeback.account.auth.api.controllers;

import fr.fae.project.memoriaeback.account.auth.api.dto.requests.LoginRequest;
import fr.fae.project.memoriaeback.account.auth.api.dto.requests.RegisterRequest;
import fr.fae.project.memoriaeback.account.auth.api.dto.responses.AuthResponse;
import fr.fae.project.memoriaeback.account.auth.application.service.IAuthService;
import fr.fae.project.memoriaeback.common.ServiceResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService authService;

    @Value("${jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    @Value("${jwt.refresh-token.cookie-secure}")
    private boolean cookieSecure;

    private static final String REFRESH_COOKIE_NAME = "refresh_token";
    private static final String COOKIE_PATH = "/api/auth";

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ServiceResponse<AuthResponse>> register(
            @Valid @RequestBody RegisterRequest request,
            HttpServletResponse response) {
        ServiceResponse<AuthResponse> result = authService.register(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<ServiceResponse<AuthResponse>> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse) {
        ServiceResponse<AuthResponse> result = authService.login(
                request,
                httpRequest.getHeader("X-Device-Id"),
                httpRequest.getRemoteAddr(),
                httpRequest.getHeader("User-Agent")
                );

        if (result.getData() != null) {
            addRefreshTokenCookie(httpResponse, result.getData().getRefreshToken());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ServiceResponse<AuthResponse>> refresh(
            @CookieValue(value = REFRESH_COOKIE_NAME, required = false) String rawToken,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse) {
        if (rawToken == null || rawToken.isBlank()) {
            return ResponseEntity.ok(new ServiceResponse<>("1106", "Invalid refresh token ", null));
        }

        ServiceResponse<AuthResponse> result = authService.refresh(
                rawToken,
                httpRequest.getHeader("X-Device-Id"),
                httpRequest.getRemoteAddr(),
                httpRequest.getHeader("User-Agent")
                );

        if(result.getData() != null) {
            addRefreshTokenCookie(httpResponse, result.getData().getRefreshToken());
        } else {
            clearRefreshTokenCookie(httpResponse);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/logout")
    public ResponseEntity<ServiceResponse<Void>> logout(
            @CookieValue(value = REFRESH_COOKIE_NAME, required = false) String rawToken,
            HttpServletResponse response) {

        if (rawToken != null && !rawToken.isBlank()) {
            authService.logout(rawToken);
        }

        clearRefreshTokenCookie(response);
        return ResponseEntity.ok(new ServiceResponse<>("1001", "Logout successful", null));
    }

    @GetMapping("/me")
    public ResponseEntity<ServiceResponse<AuthResponse>> me(
            Authentication authentication
    ){
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity
                    .status(401)
                    .body(new ServiceResponse<>("1300", "Access denied — not authenticated", null));
        }
        return ResponseEntity.ok(authService.me(authentication.getName()));
    }

    private void addRefreshTokenCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie
                .from(REFRESH_COOKIE_NAME, token)
                .httpOnly(true)
                .secure(cookieSecure)
                .path(COOKIE_PATH)
                .sameSite("Strict")
                .maxAge(Duration.ofMillis(refreshTokenExpiration))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    private void clearRefreshTokenCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie
                .from(REFRESH_COOKIE_NAME, "")
                .httpOnly(true)
                .secure(cookieSecure)
                .path(COOKIE_PATH)
                .sameSite("Strict")
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

}
