# backend-level-one
UNDER CONSTRUCTION

Initial setup:
Postgres DB:
docker run --detach --restart unless-stopped --name da-learn --env POSTGRES_PASSWORD=docker --publish 5432:5432 --volume $HOME/docker/volumes/da-learn/postgres:/var/lib/postgresql/data postgres:16.2

create database:
DROP ROLE IF EXISTS da_learn;
CREATE ROLE da_learn WITH PASSWORD 'password' LOGIN CREATEDB;
DROP DATABASE IF EXISTS backend_one;
CREATE DATABASE backend_one;
GRANT ALL ON DATABASE backend_one TO da_learn;

CREATE TABLE character
(
id             UUID PRIMARY KEY,
name           VARCHAR(255) NOT NULL,
species        VARCHAR(255) NOT NULL,
character_type VARCHAR(255) NOT NULL,
origin_game    VARCHAR(255) NOT NULL,
created_year   INT          NOT NULL
);




FLOW OF TUTOTIAL
1) run the get - no characters
2) lets create some - basic post that creates like 5
3) small post creates one - let them chose a character and pass it in request
4) post to update fields with correct data for new character
5) delete a character 