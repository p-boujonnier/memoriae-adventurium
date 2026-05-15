package fr.fae.project.memoriaeback.features.libria.controller;

import fr.fae.project.memoriaeback.features.libria.client.LibriaClient;
import fr.fae.project.memoriaeback.features.libria.dto.MagazineDetailDto;
import fr.fae.project.memoriaeback.features.libria.dto.MagazineListItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/libria")
public class LibriaController {

    private final LibriaClient libriaClient;

    public LibriaController(LibriaClient libriaClient) {
        this.libriaClient = libriaClient;
    }

    @GetMapping("/dragons")
    public ResponseEntity<List<MagazineListItemDto>> getDragons() {
        return ResponseEntity.ok(libriaClient.getDragons());
    }

    @GetMapping("/dragons/{id}")
    public ResponseEntity<MagazineDetailDto> getDragon(@PathVariable int id) {
        return ResponseEntity.ok(libriaClient.getDragon(id));
    }

    @GetMapping("/dungeons")
    public ResponseEntity<List<MagazineListItemDto>> getDungeons() {
        return ResponseEntity.ok(libriaClient.getDungeons());
    }

    @GetMapping("/dungeons/{id}")
    public ResponseEntity<MagazineDetailDto> getDungeon(@PathVariable int id) {
        return ResponseEntity.ok(libriaClient.getDungeon(id));
    }
}
