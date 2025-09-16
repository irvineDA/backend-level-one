package com.da_learn.backend.level.one.controller;

import com.da_learn.backend.level.one.model.TrackEntity;
import com.da_learn.backend.level.one.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService TrackService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<TrackEntity>> getAllTracks() {
        return ResponseEntity.ok(TrackService.getAllTracks());
    }

    @GetMapping("/{TrackName}")
    public ResponseEntity<TrackEntity> getTrackByName(@PathVariable String TrackName) {
        return ResponseEntity.ok(TrackService.getTrack(TrackName));
    }

    @PostMapping("/saveAll")
    public ResponseEntity<Void> saveDefaultTracks() {
        TrackService.saveDefaultTracks();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/new")
    public ResponseEntity<TrackEntity> saveTrack(@RequestBody TrackEntity Track) {
        return ResponseEntity.status(201).body(TrackService.saveTrack(Track));
    }

    @DeleteMapping("/delete/{TrackName}")
    public ResponseEntity<Void> deleteTrack(@PathVariable String TrackName) {
        TrackService.deleteTrack(TrackName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllTracks() {
        TrackService.deleteAllTracks();
        return ResponseEntity.noContent().build();
    }
}
