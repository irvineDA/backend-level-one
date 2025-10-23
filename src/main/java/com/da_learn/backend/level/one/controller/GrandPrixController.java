package com.da_learn.backend.level.one.controller;

import com.da_learn.backend.level.one.model.dto.GrandPrixResult;
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
    public ResponseEntity<GrandPrixResult> startGrandPrix() {
        return ResponseEntity.ok(grandPrixService.start());
    }
}
