package com.da_learn.backend.level.one.repository;

import com.da_learn.backend.level.one.model.CharacterEntity;
import com.da_learn.backend.level.one.model.TrackEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TrackRepository extends CrudRepository<TrackEntity, Long> {
    Optional<TrackEntity> findByName(String name);
    Boolean existsByName(String name);
    void deleteByName(String name);
}
