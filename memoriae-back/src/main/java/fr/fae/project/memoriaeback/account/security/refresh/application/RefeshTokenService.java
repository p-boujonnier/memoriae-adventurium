package fr.fae.project.memoriaeback.account.security.refresh.application;

import fr.fae.project.memoriaeback.account.security.refresh.domain.models.RefreshToken;
import fr.fae.project.memoriaeback.account.security.refresh.domain.repositories.IRefreshTokenRepository;
import fr.fae.project.memoriaeback.account.user.domain.models.User;
import fr.fae.project.memoriaeback.common.ServiceResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.HexFormat;
import java.util.Optional;

@Service
public class RefeshTokenService implements IRefreshTokenService {

    private final IRefreshTokenRepository refreshTokenRepository;
    @Value("${jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;
    private final SecureRandom random = new SecureRandom();

    public RefeshTokenService(IRefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    /**
     * Generates a secure refresh token for the given user and device information.
     * @param user The user for whom the token is being generated.
     * @param device The device identifier for the token.
     * @param ipAddress The IP address associated with the token.
     * @param userAgent The user agent string for the token.
     * @return A ServiceResponse containing the generated refresh token.
     */
    @Override @Transactional
    public ServiceResponse<String> createAndPersistRefreshToken(
            User user, String device, String ipAddress, String userAgent) {
        String raw = generateSecureToken();
        String hash = hashToken(raw);

        RefreshToken rt = new RefreshToken(
                user,
                hash,
                device,
                ipAddress,
                userAgent,
                refreshTokenExpiration);

        refreshTokenRepository.save(rt);

        return new ServiceResponse<>("1000", "Refresh token created successfully", raw);

    }

    /**
     * Rotates the refresh token for the given raw token and device information.
     * @param rawToken The raw token to be rotated.
     * @param device The device identifier for the token.
     * @param ipAddress The IP address associated with the token.
     * @param userAgent The user agent string for the token.
     * @return A ServiceResponse containing the rotated refresh token.
     */
    @Override
    public ServiceResponse<String> rotateRefreshToken(
            String rawToken, String device, String ipAddress, String userAgent) {
        String hash = hashToken(rawToken);

        Optional<RefreshToken> rtFound = refreshTokenRepository.findByTokenHash(hash);

        if (rtFound.isEmpty()) {
            return new ServiceResponse<>("1106","Invalid refresh token",null);
        }

        RefreshToken oldRt = rtFound.get();

        if (oldRt.isRevoked()) {
            refreshTokenRepository.revokeAllByUser(oldRt.getUser());
            return new ServiceResponse<>("1106","Invalid refresh token",null);
        }

        if (oldRt.getExpiresAt().isBefore(OffsetDateTime.now())) {
            return new ServiceResponse<>("1107","Refresh token expired",null);
        }

        if (!oldRt.getDevice().equals(device)){
            refreshTokenRepository.revokeAllByUser(oldRt.getUser());
            return new ServiceResponse<>("1106","Invalid refresh token",null);
        }

        if (oldRt.getIpAddress() != null && !oldRt.getIpAddress().equals(ipAddress)) {
            refreshTokenRepository.revokeAllByUser(oldRt.getUser());
            return new ServiceResponse<>("1106","Invalid refresh token",null);
        }

        if(oldRt.getUserAgent() != null && !oldRt.getUserAgent().equals(userAgent)) {
            refreshTokenRepository.revokeAllByUser(oldRt.getUser());
            return new ServiceResponse<>("1106","Invalid refresh token",null);
        }

        String newRaw = generateSecureToken();
        String newHash = hashToken(newRaw);

        RefreshToken newRt = new RefreshToken(
                oldRt.getUser(),
                newHash,
                device,
                ipAddress,
                userAgent,
                refreshTokenExpiration
        );
        refreshTokenRepository.save(newRt);

        oldRt.setRevoked(true);
        oldRt.setReplacedByTokenHash(newHash);
        oldRt.setLastUsedAt(OffsetDateTime.now());
        refreshTokenRepository.save(oldRt);

        return new ServiceResponse<>("1002", "Refresh token rotated successfully", newRaw);
    }

    /**
     * Revoke a refresh token by its raw value.
     * @param rawToken The raw token value to be revoked.
     * @return ServiceResponse indicating success or failure.
     */
    @Override
    public ServiceResponse<Void> revokeRefreshToken(String rawToken) {
        refreshTokenRepository.revokeByTokenHash(hashToken(rawToken));
        return new ServiceResponse<>("1001", "Logout successful", null);
    }

    /**
     * Revoke all refresh tokens associated with a user.
     * @param user The user for whom to revoke all refresh tokens.
     * @return ServiceResponse indicating success or failure.
     */
    @Override
    public ServiceResponse<Void> revokeAllForUser(User user) {
        refreshTokenRepository.revokeAllByUser(user);
        return new ServiceResponse<>("1001", "Logout successful", null);
    }

    /**
     * Find a valid refresh token by its raw value.
     * @param rawToken The raw token value to find.
     * @return ServiceResponse containing the valid refresh token or an error.
     */
    @Override
    public ServiceResponse<RefreshToken> findValidByToken(String rawToken) {
        Optional<RefreshToken> rtFound = refreshTokenRepository.findByTokenHash(hashToken(rawToken));

        if (rtFound.isEmpty() || rtFound.get().isRevoked()) {
            return new ServiceResponse<>("1106","Invalid refresh token",null);
        }
        if (rtFound.get().getExpiresAt().isBefore(OffsetDateTime.now())) {
            return new ServiceResponse<>("1107","Refresh token expired",null);
        }
        return new ServiceResponse<>("1002", "Token retrieved successfully", rtFound.get());
    }

    /**
     * Generate a secure random token.
     * @return A secure random token string.
     */
    private String generateSecureToken() {
        byte[] bytes = new byte[64];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    /**
     * Hash a raw token using SHA-256.
     * @param rawToken The raw token to hash.
     * @return The hashed token as a hexadecimal string.
     */
    private String hashToken(String rawToken) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawToken.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Impossible de générer un hash SHA-256", ex);
        }
    }
}
