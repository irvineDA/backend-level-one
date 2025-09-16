CREATE TABLE track
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(255),
    cup            VARCHAR(255),
    theme          VARCHAR(255),
    laps           INT,
    difficulty     VARCHAR(255)
);