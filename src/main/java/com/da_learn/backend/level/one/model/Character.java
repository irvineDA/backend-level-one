package com.da_learn.backend.level.one.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "characters")
@DynamicUpdate
public class Character extends CommonIdentifierEntity {

    @NotNull private UUID id;

    @Size(max = 255)
    @NotNull
    private String name;

    @Size(max = 255)
    @NotNull
    private String species;

    @Size(max = 255)
    @NotNull
    private String character_type;

    @Size(max = 255)
    @NotNull
    private String origin_game;

    @NotNull
    private Integer created_year;

    @Override
    public Character setId(UUID id) {
        super.setId(id);
        return this;
    }

    @Override
    public Character setIdentifier(Long identifier) {
        super.setIdentifier(identifier);
        return this;
    }
}
