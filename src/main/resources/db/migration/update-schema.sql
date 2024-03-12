ALTER TABLE organisation_players
    DROP CONSTRAINT fk6uber8vyjyr55gt3maveeutsr;

ALTER TABLE organisation_players
    DROP CONSTRAINT fkc7iica9k4uddcjnr7jfgs2ywk;

ALTER TABLE player_player_organisations
    DROP CONSTRAINT fkghchsrb96bytq2y1lra1ht5f5;

ALTER TABLE player_player_organisations
    DROP CONSTRAINT fkpov80md4vro2d0ire6x3jbkyt;

ALTER TABLE game
    ADD organisation_id BIGINT;

ALTER TABLE game
    ALTER COLUMN organisation_id SET NOT NULL;

DROP TABLE hibernate_sequences CASCADE;

ALTER TABLE favorite
    ADD CONSTRAINT FK_FAVORITE_ON_ID FOREIGN KEY (id) REFERENCES card (id);

ALTER TABLE game
    ADD CONSTRAINT FK_GAME_ON_ORGANISATION FOREIGN KEY (organisation_id) REFERENCES organisation (id);

DROP TABLE organisation_players CASCADE;

DROP TABLE player CASCADE;

DROP TABLE player_player_organisations CASCADE;

ALTER TABLE card
    DROP COLUMN formation_link;
