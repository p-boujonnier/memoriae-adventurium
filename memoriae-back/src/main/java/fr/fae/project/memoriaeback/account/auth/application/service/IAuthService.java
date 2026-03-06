package fr.fae.project.memoriaeback.account.auth.application.service;

import fr.fae.project.memoriaeback.account.auth.api.dtos.requests.LoginRequest;
import fr.fae.project.memoriaeback.account.auth.api.dtos.requests.RegisterRequest;
import fr.fae.project.memoriaeback.account.auth.api.dtos.responses.AuthResponse;
import fr.fae.project.memoriaeback.account.user.domain.models.User;
import fr.fae.project.memoriaeback.common.ServiceResponse;

public interface IAuthService {

    ServiceResponse<AuthResponse> register(RegisterRequest request);

    ServiceResponse<AuthResponse> login(LoginRequest request, String device, String ipAddress, String userAgent);

    ServiceResponse<Void> logout(String rawRefreshToken);

    ServiceResponse<AuthResponse> refresh(String rawRefreshToken, String device, String ipAddress, String userAgent);

    ServiceResponse<AuthResponse> me(String userId);

}