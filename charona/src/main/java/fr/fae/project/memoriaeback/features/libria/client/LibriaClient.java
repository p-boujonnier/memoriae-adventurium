package fr.fae.project.memoriaeback.features.client;

import fr.fae.project.memoriaeback.features.dto.MagazineDetailDto;
import fr.fae.project.memoriaeback.features.dto.MagazineListItemDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class LibriaClient {

    private final RestClient restClient;

    public LibriaClient(@Qualifier("libriaRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public List<MagazineListItemDto> getDragons() {
        return restClient.get()
                .uri("/dragons")
                .retrieve()
                .body(new ParameterizedTypeReference<List<MagazineListItemDto>>() {});
    }

    public MagazineDetailDto getDragon(int id) {
        return restClient.get()
                .uri("/dragons/{id}", id)
                .retrieve()
                .body(MagazineDetailDto.class);
    }

    public List<MagazineListItemDto> getDungeons() {
        return restClient.get()
                .uri("/dungeons")
                .retrieve()
                .body(new ParameterizedTypeReference<List<MagazineListItemDto>>() {});
    }

    public MagazineDetailDto getDungeon(int id) {
        return restClient.get()
                .uri("/dungeons/{id}", id)
                .retrieve()
                .body(MagazineDetailDto.class);
    }
}
