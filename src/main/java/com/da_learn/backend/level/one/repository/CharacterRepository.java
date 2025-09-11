package com.da_learn.backend.level.one.repository;

import com.da_learn.backend.level.one.model.CharacterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CharacterRepository extends CrudRepository<CharacterEntity, Long> {
    Optional<CharacterEntity> findByName(String name);
    Boolean existsByName(String name);
    void deleteByName(String name);
}

