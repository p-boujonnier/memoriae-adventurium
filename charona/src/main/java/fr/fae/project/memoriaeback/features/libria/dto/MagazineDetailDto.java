package fr.fae.project.memoriaeback.features.dto;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MagazineDetailDto(
        Integer id,
        Integer issue,
        String source,
        String magazine,
        Integer totalPages,
        String language,
        Integer year,
        String pdfUrl,
        String coverUrl
) {}