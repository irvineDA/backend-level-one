package com.da_learn.backend.level.one.repository;

import com.da_learn.backend.level.one.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    Character findByName(String name);
    Boolean existsByName(String name);
    void deleteByName(String name);
}

