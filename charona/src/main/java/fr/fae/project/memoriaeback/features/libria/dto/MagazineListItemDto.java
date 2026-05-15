package fr.fae.project.memoriaeback.features.dto;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MagazineListItemDto(
        Integer id,
        Integer issue,
        String coverUrl
) {}