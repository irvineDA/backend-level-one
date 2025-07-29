package com.da_learn.backend.level.one.service;

import com.da_learn.backend.level.one.model.CharacterEntity;
import com.da_learn.backend.level.one.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;

    public Iterable<CharacterEntity> getAllCharacters() {
        return characterRepository.findAll();
    }

    public CharacterEntity getCharacter(String name) {
        if(characterRepository.existsByName(name)) {
            return characterRepository.findByName(name);
        }
        return CharacterEntity.builder().build();
    }

    @Transactional
    public void saveDefaultCharacters() {
        if (characterRepository.count() == 0) {
            characterRepository.saveAll(List.of(
                CharacterEntity.builder().name("Mario").species("Human").character_type("Hero").origin_game("Donkey Kong").created_year(1985).build(),
                CharacterEntity.builder().name("Luigi").species("Human").character_type("Sidekick").origin_game("Super Mario Bros.").created_year(1985).build(),
                CharacterEntity.builder().name("Peach").species("Human").character_type("Princess").origin_game("Super Mario Bros.").created_year(1985).build(),
                CharacterEntity.builder().name("Bowser").species("Koopa").character_type("Villain").origin_game("Super Mario Bros.").created_year(1985).build(),
                CharacterEntity.builder().name("Yoshi").species("Dinosaur").character_type("Sidekick").origin_game("Super Mario World").created_year(1990).build()
            ));
        }
    }

    @Transactional
    public CharacterEntity saveCharacter(CharacterEntity character) {
        return characterRepository.save(character);
    }

    @Transactional
    public void deleteCharacter(String name) {
        if (characterRepository.existsByName(name)) {
            characterRepository.deleteByName(name);
        }
    }

    @Transactional
    public void deleteAllCharacters() {
        characterRepository.deleteAll();
    }
}

