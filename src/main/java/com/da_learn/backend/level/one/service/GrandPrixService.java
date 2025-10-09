package com.da_learn.backend.level.one.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GrandPrixService {
    public void start(String difficulty) {
        log.info("Starting Grand Prix with difficulty: {}", difficulty);
        // Implement the logic to start the Grand Prix based on the difficulty level
    }
}
