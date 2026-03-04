package fr.fae.project.memoriaeback.account.user.api.mappers;

import fr.fae.project.memoriaeback.account.user.api.dtos.requests.UserCreateRequest;
import fr.fae.project.memoriaeback.account.user.api.dtos.responses.UserPublicResponse;
import fr.fae.project.memoriaeback.account.user.domain.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest userCreateRequest);

    UserPublicResponse toUserResponse(User user);
}
