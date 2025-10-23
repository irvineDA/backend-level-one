package com.da_learn.backend.level.one.service;

import com.da_learn.backend.level.one.model.CharacterEntity;
import com.da_learn.backend.level.one.model.TrackEntity;
import com.da_learn.backend.level.one.model.dto.GrandPrixResult;
import com.da_learn.backend.level.one.model.dto.ParticipantResult;
import com.da_learn.backend.level.one.model.dto.RacePosition;
import com.da_learn.backend.level.one.model.dto.RaceResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GrandPrixService {

  private final TrackService trackService;
  private final CharacterService characterService;

  private static final int[] POINTS = {15, 12, 10, 8, 6};

  public GrandPrixResult start() {
    log.info("Starting Grand Prix");

    ArrayList<TrackEntity> tracks = trackService.getRandomTracks(4);
    ArrayList<CharacterEntity> characters = characterService.getRandomCharacters(5);

    log.info(
        "Grand Prix initialized with {} tracks and {} characters",
        tracks.size(),
        characters.size());

    return generateGrandPrixResult(tracks, characters);
  }

  private GrandPrixResult generateGrandPrixResult(ArrayList<TrackEntity> tracks, ArrayList<CharacterEntity> characters) {
    // Initialize results map with all characters at 0 points
    Map<String, Integer> pointsMap = new HashMap<>();
    for (CharacterEntity character : characters) {
      pointsMap.put(character.getName(), 0);
    }

    // Store race results for each track
    List<RaceResult> raceResults = new ArrayList<>();

    // Simulate each race
    for (TrackEntity track : tracks) {
      RaceResult raceResult = simulateRace(track, characters, pointsMap);
      raceResults.add(raceResult);
    }

    // Create final standings sorted by points descending
    List<ParticipantResult> finalStandings =
        pointsMap.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .map(
                entry ->
                    ParticipantResult.builder()
                        .characterName(entry.getKey())
                        .totalPoints(entry.getValue())
                        .build())
            .collect(Collectors.toList());

    // Build and return the complete result
    log.info("Grand Prix completed. Generating final results.");
    return GrandPrixResult.builder()
        .tracks(tracks.stream().map(TrackEntity::getName).collect(Collectors.toList()))
        .participants(
            characters.stream().map(CharacterEntity::getName).collect(Collectors.toList()))
        .finalStandings(finalStandings)
        .raceResults(raceResults)
        .build();
  }

  private RaceResult simulateRace(
      TrackEntity track, ArrayList<CharacterEntity> characters, Map<String, Integer> pointsMap) {
    // Shuffle characters to randomize finishing positions
    List<CharacterEntity> shuffled = new ArrayList<>(characters);
    Collections.shuffle(shuffled);

    List<RacePosition> positions = new ArrayList<>();

    // Assign points based on finishing position
    for (int i = 0; i < shuffled.size() && i < POINTS.length; i++) {
      String characterName = shuffled.get(i).getName();
      int pointsEarned = POINTS[i];
      pointsMap.put(characterName, pointsMap.get(characterName) + pointsEarned);

      positions.add(
          RacePosition.builder()
              .position(i + 1)
              .characterName(characterName)
              .pointsEarned(pointsEarned)
              .build());
    }

    return RaceResult.builder().trackName(track.getName()).positions(positions).build();
  }
}
