package fr.fae.project.memoriaeback.account.security.refresh.domain.repositories;

import fr.fae.project.memoriaeback.account.security.refresh.domain.models.RefreshToken;
import fr.fae.project.memoriaeback.account.user.domain.models.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
@Profile("jpa")
public class RefreshTokenRepositoryJpaAdapter implements IRefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    public RefreshTokenRepositoryJpaAdapter(RefreshTokenJpaRepository refreshTokenJpaRepository) {
        this.refreshTokenJpaRepository = refreshTokenJpaRepository;
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenJpaRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByTokenHash(String tokenHash) {
        return refreshTokenJpaRepository.findByTokenHash(tokenHash);
    }

    @Override
    public int revokeAllByUser(User user) {
        return refreshTokenJpaRepository.revokeAllByUser(user);
    }

    @Override
    public int revokeByTokenHash(String tokenHash) {
        return refreshTokenJpaRepository.revokeByTokenHash(tokenHash);
    }

    @Override
    public void deleteAllByExpiresAtBefore(OffsetDateTime dateTime) {
        refreshTokenJpaRepository.deleteAllByExpiresAtBefore(dateTime);
    }
}
