CREATE TABLE character
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(255),
    species        VARCHAR(255),
    character_type VARCHAR(255),
    origin_game    VARCHAR(255),
    created_year   INT
);