package com.da_learn.backend.level.one.controller;

import com.da_learn.backend.level.one.service.GrandPrixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grand-prix")
@RequiredArgsConstructor
public class GrandPrixController {
    private final GrandPrixService grandPrixService;

    @PostMapping("/start")
    public ResponseEntity<Void> startGrandPrix(@PathVariable String difficulty) {
        grandPrixService.start(difficulty);
        // THIS will return the race outcome in the JSON payload
        // present to user instead of void
        return ResponseEntity.noContent().build();
    }

}
