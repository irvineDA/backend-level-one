package com.da_learn.backend.level.one.controller;

import com.da_learn.backend.level.one.service.CharacterService;
import com.da_learn.backend.level.one.model.Character;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/all")
    public ResponseEntity<List<Character>> getAllCharacters() {
        return ResponseEntity.ok(characterService.getAllCharacters());
    }

    @GetMapping("/{characterName}")
    public ResponseEntity<Character> getCharacterByName(@PathVariable String characterName) {
        return ResponseEntity.ok(characterService.getCharacter(characterName));
    }

    @PostMapping("/saveAll")
    public ResponseEntity<Void> saveDefaultCharacters() {
        characterService.saveDefaultCharacters();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/new")
    public ResponseEntity<Character> saveCharacter(@RequestBody Character character) {
        Character created = characterService.saveCharacter(character);
        return ResponseEntity.created(
                        URI.create("/characters/new"))
                .body(created);
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

