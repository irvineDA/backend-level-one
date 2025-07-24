CREATE TABLE characters
(
    id             UUID PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    species        VARCHAR(255) NOT NULL,
    character_type VARCHAR(255) NOT NULL,
    origin_game    VARCHAR(255) NOT NULL,
    created_year   INT          NOT NULL,
    identifier     BIGINT UNIQUE
);