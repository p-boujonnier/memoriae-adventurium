package fr.fae.project.memoriaeback.personage.api.mappers;

import fr.fae.project.memoriaeback.personage.api.dto.requests.PersonageCreateRequest;
import fr.fae.project.memoriaeback.personage.api.dto.requests.PersonageUpdateRequest;
import fr.fae.project.memoriaeback.personage.api.dto.responses.PersonageResponse;
import fr.fae.project.memoriaeback.personage.domain.models.Personage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonageMapper {
    Personage toPersonageFromCreate(PersonageCreateRequest personageCreateRequest);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    void toPersonageFromUpdate(PersonageUpdateRequest dto, @MappingTarget Personage personage);

    PersonageResponse toResponse(Personage personage);
}

