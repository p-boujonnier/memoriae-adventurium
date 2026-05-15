package fr.fae.project.memoriaeback.account.security.refresh.domain.repositories;

import fr.fae.project.memoriaeback.account.security.refresh.domain.models.RefreshToken;
import fr.fae.project.memoriaeback.account.user.domain.models.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("mock")
public class RefreshTokenRepositoryMock implements IRefreshTokenRepository{

    private final List<RefreshToken> tokens = new ArrayList<>();

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        tokens.removeIf(rt -> rt.getTokenHash().equals(refreshToken.getTokenHash()));
        tokens.add(refreshToken);
        return refreshToken;
    }

    @Override
    public Optional<RefreshToken> findByTokenHash(String tokenHash) {
        return tokens
                .stream()
                .filter(rt -> rt.getTokenHash().equals(tokenHash))
                .findFirst();
    }

    @Override
    public int revokeAllByUser(User user) {
        List<RefreshToken> foundTokens = tokens
                .stream()
                .filter(rt -> rt.getUser().getId().equals(user.getId()) && !rt.isRevoked())
                .toList();
        foundTokens.forEach(rt -> rt.setRevoked(true));
        return foundTokens.size();
    }

    @Override
    public int revokeByTokenHash(String tokenHash) {
        return tokens
                .stream()
                .filter(rt -> rt.getTokenHash().equals(tokenHash) && !rt.isRevoked())
                .findFirst()
                .map(rt -> {
                    rt.setRevoked(true);
                    return 1;
                })
                .orElse(0);
    }

    @Override
    public void deleteAllByExpiresAtBefore(OffsetDateTime dateTime) {
        tokens.removeIf(rt -> rt.getExpiresAt().isBefore(dateTime));
    }
}
