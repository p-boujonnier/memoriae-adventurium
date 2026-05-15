package fr.fae.project.memoriaeback.account.security.refresh.application;

import fr.fae.project.memoriaeback.account.security.refresh.domain.models.RefreshToken;
import fr.fae.project.memoriaeback.account.user.domain.models.User;
import fr.fae.project.memoriaeback.common.ServiceResponse;

public interface IRefreshTokenService {

    ServiceResponse<String> createAndPersistRefreshToken(User user, String device, String ipAddress, String userAgent);

    ServiceResponse<String> rotateRefreshToken(String rawToken, String device, String ipAddress, String userAgent);

    ServiceResponse<Void> revokeRefreshToken(String rawToken);

    ServiceResponse<Void> revokeAllForUser(User user);

    ServiceResponse<RefreshToken> findValidByToken(String rawToken);
}
