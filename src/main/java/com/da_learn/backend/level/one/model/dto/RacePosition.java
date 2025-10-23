package com.da_learn.backend.level.one.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RacePosition {
    private int position;
    private String characterName;
    private int pointsEarned;
}
