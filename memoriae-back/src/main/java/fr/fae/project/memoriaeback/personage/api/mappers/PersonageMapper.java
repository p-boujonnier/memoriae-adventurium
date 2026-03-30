package fr.fae.project.memoriaeback.personage.api.mappers;

import fr.fae.project.memoriaeback.personage.api.dto.requests.PersonageCreateRequest;
import fr.fae.project.memoriaeback.personage.api.dto.requests.PersonageUpdateRequest;
import fr.fae.project.memoriaeback.personage.api.dto.responses.PersonageResponse;
import fr.fae.project.memoriaeback.personage.domain.models.Personage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonageMapper {
    Personage toPersonageFromCreate(PersonageCreateRequest personageCreateRequest);
    Personage toPersonageFromUpdate(PersonageUpdateRequest personageUpdateRequest);

    PersonageResponse toResponse(Personage personage);
}

