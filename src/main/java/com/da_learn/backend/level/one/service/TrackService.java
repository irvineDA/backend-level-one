package com.da_learn.backend.level.one.service;

import com.da_learn.backend.level.one.exception.handler.TrackNotFoundException;
import com.da_learn.backend.level.one.model.TrackEntity;
import com.da_learn.backend.level.one.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrackService {
    private final TrackRepository TrackRepository;

    public Iterable<TrackEntity> getAllTracks() {
        log.info("Fetching all Tracks");
        return TrackRepository.findAll();
    }

    public TrackEntity getTrack(String name) {
        return TrackRepository.findByName(name)
                .orElseThrow(() -> new TrackNotFoundException(name));
    }

    @Transactional
    public void saveDefaultTracks() {
        if (TrackRepository.count() == 0) {
            log.info("Seeding default Tracks...");
            TrackRepository.saveAll(List.of(
                TrackEntity.builder().name("Rainbow Road").cup("Special Cup").theme("Space").laps(3).difficulty("Expert").build(),
                TrackEntity.builder().name("Bowser's Castle").cup("Star Cup").theme("Castle/Lava").laps(3).difficulty("Hard").build(),
                TrackEntity.builder().name("Moo Moo Meadows").cup("Mushroom Cup").theme("Farm/Grassland").laps(3).difficulty("Easy").build(),
                TrackEntity.builder().name("DK Mountain").cup("Special Cup").theme("Jungle/Volcano").laps(3).difficulty("Hard").build(),
                TrackEntity.builder().name("Yoshi Circuit").cup("Special Cup").theme("Island/Road").laps(3).difficulty("Medium").build(),
                TrackEntity.builder().name("Wario Stadium").cup("Star Cup").theme("Stadium/Dirt").laps(3).difficulty("Medium").build(),
                TrackEntity.builder().name("Toad's Turnpike").cup("Flower Cup").theme("Highway/City").laps(3).difficulty("Medium").build(),
                TrackEntity.builder().name("Luigi Circuit").cup("Mushroom Cup").theme("Circuit/Track").laps(3).difficulty("Easy").build()
            ));
        } else {
            log.info("Default Tracks already exist, skipping seeding");
        }
    }

    @Transactional
    public TrackEntity saveTrack(TrackEntity Track) {
        log.info("Saving new Track: {}", Track.getName());
        return TrackRepository.save(Track);
    }

    @Transactional
    public void deleteTrack(String name) {
        if (TrackRepository.existsByName(name)) {
            log.info("Deleting Track: {}", name);
            TrackRepository.deleteByName(name);
        } else {
            throw new TrackNotFoundException(name);
        }
    }

    @Transactional
    public void deleteAllTracks() {
        log.warn("Deleting ALL Tracks");
        TrackRepository.deleteAll();
    }
}
