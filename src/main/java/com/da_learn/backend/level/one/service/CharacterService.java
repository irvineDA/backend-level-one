package com.da_learn.backend.level.one.service;

import com.da_learn.backend.level.one.model.Character;
import com.da_learn.backend.level.one.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public Character getCharacter(String name) {
        if(characterRepository.existsByName(name)) {
            return characterRepository.findByName(name);
        }
        return Character.builder().build();
    }


    @Transactional
    public void saveDefaultCharacters() {
        if (characterRepository.count() == 0) {
            characterRepository.saveAll(List.of(
                Character.builder().name("Mario").species("Human").character_type("Hero").origin_game("Donkey Kong").created_year(1985).build(),
                Character.builder().name("Luigi").species("Human").character_type("Sidekick").origin_game("Super Mario Bros.").created_year(1985).build(),
                Character.builder().name("Peach").species("Human").character_type("Princess").origin_game("Super Mario Bros.").created_year(1985).build(),
                Character.builder().name("Bowser").species("Koopa").character_type("Villain").origin_game("Super Mario Bros.").created_year(1985).build(),
                Character.builder().name("Yoshi").species("Dinosaur").character_type("Sidekick").origin_game("Super Mario World").created_year(1990).build()
            ));
        }
    }

    @Transactional
    public Character saveCharacter(Character character) {
        character.setId(UUID.randomUUID());
        characterRepository.save(character);
        return character;
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

    //need better validation/null record handling

}

