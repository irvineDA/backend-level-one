package com.da_learn.backend.level.one.controller;

import com.da_learn.backend.level.one.model.CharacterEntity;
import com.da_learn.backend.level.one.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<CharacterEntity>> getAllCharacters() {
        return ResponseEntity.ok(characterService.getAllCharacters());
    }

    @GetMapping("/{characterName}")
    public ResponseEntity<CharacterEntity> getCharacterByName(@PathVariable String characterName) {
        return ResponseEntity.ok(characterService.getCharacter(characterName));
    }

    @PostMapping("/saveAll")
    public ResponseEntity<Void> saveDefaultCharacters() {
        characterService.saveDefaultCharacters();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/new")
    public ResponseEntity<CharacterEntity> saveCharacter(@RequestBody CharacterEntity character) {
        return ResponseEntity.ok(characterService.saveCharacter(character));
    }

    @DeleteMapping("/delete/{characterName}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable String characterName) {
        characterService.deleteCharacter(characterName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllCharacters() {
        characterService.deleteAllCharacters();
        return ResponseEntity.noContent().build();
    }
}

