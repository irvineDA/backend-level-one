package com.da_learn.backend.level.one.service;

import com.da_learn.backend.level.one.exception.handler.CharacterNotFoundException;
import com.da_learn.backend.level.one.model.CharacterEntity;
import com.da_learn.backend.level.one.repository.CharacterRepository;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;

    public Iterable<CharacterEntity> getAllCharacters() {
        log.info("Fetching all characters");
        return characterRepository.findAll();
    }

    public ArrayList<CharacterEntity> getRandomCharacters(int count) {
        log.info("Retrieving up to {} random characters", count);

        List<CharacterEntity> allCharacters = Lists.newArrayList(characterRepository.findAll());

        if (allCharacters.size() <= 1) {
            log.warn("Not enough characters available to race: {}", allCharacters.size());
            throw new IllegalStateException("Need at least 2 characters");
        }

        Collections.shuffle(allCharacters);

        // Return up to count characters (or all if fewer)
        int actualCount = Math.min(count, allCharacters.size());
        return new ArrayList<>(allCharacters.subList(0, actualCount));
    }

    public CharacterEntity getCharacter(String name) {
        return characterRepository.findByName(name)
                .orElseThrow(() -> new CharacterNotFoundException(name));
    }

    @Transactional
    public void saveDefaultCharacters() {
        if (characterRepository.count() == 0) {
            log.info("Seeding default characters...");
            characterRepository.saveAll(List.of(
                    CharacterEntity.builder().name("Mario").species("Human").character_type("Hero").origin_game("Donkey Kong").created_year(1985).build(),
                    CharacterEntity.builder().name("Luigi").species("Human").character_type("Sidekick").origin_game("Super Mario Bros.").created_year(1985).build(),
                    CharacterEntity.builder().name("Peach").species("Human").character_type("Princess").origin_game("Super Mario Bros.").created_year(1985).build(),
                    CharacterEntity.builder().name("Bowser").species("Koopa").character_type("Villain").origin_game("Super Mario Bros.").created_year(1985).build(),
                    CharacterEntity.builder().name("Yoshi").species("Dinosaur").character_type("Sidekick").origin_game("Super Mario World").created_year(1990).build()
            ));
        } else {
            log.info("Default characters already exist, skipping seeding");
        }
    }

    @Transactional
    public CharacterEntity saveCharacter(CharacterEntity character) {
        log.info("Saving new character: {}", character.getName());
        return characterRepository.save(character);
    }

    @Transactional
    public void deleteCharacter(String name) {
        if (characterRepository.existsByName(name)) {
            log.info("Deleting character: {}", name);
            characterRepository.deleteByName(name);
        } else {
            throw new CharacterNotFoundException(name);
        }
    }

    @Transactional
    public void deleteAllCharacters() {
        log.warn("Deleting ALL characters");
        characterRepository.deleteAll();
    }
}
