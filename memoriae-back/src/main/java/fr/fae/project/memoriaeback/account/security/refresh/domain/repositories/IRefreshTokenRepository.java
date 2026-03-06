package fr.fae.project.memoriaeback.account.security.refresh.domain.repositories;

import fr.fae.project.memoriaeback.account.security.refresh.domain.models.RefreshToken;
import fr.fae.project.memoriaeback.account.user.domain.models.User;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface IRefreshTokenRepository {
    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByTokenHash(String tokenHash);

    boolean revokeAllByUser(User user);

    boolean revokeByTokenHash(String tokenHash);

    void deleteAllByExpiredAtBefore(OffsetDateTime dateTime);
}

