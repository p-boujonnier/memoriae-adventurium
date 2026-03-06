package fr.fae.project.memoriaeback.account.security.refresh.domain.repositories;

import fr.fae.project.memoriaeback.account.security.refresh.domain.models.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenRepository {
    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByTokenHash(String tokenHash);

    int revokeAllByUser(RefreshToken refreshToken);

    int revokeByTokenHash(String tokenHash);

    void deleteAllByExpiredAtBefore(RefreshToken refreshToken);
}

