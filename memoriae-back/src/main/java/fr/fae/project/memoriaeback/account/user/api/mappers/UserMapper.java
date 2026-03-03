package fr.fae.project.memoriaeback.account.user.api.mappers;

import fr.fae.project.memoriaeback.account.user.api.dtos.requests.UserRequest;
import fr.fae.project.memoriaeback.account.user.api.dtos.responses.UserResponse;
import fr.fae.project.memoriaeback.account.user.domain.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest userCreateRequest);

    UserResponse toUserResponse(User user);
}
