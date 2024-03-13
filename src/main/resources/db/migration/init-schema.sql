CREATE SEQUENCE IF NOT EXISTS card_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS favorite_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS game_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS organisation_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE card
(
    id               BIGINT       NOT NULL,
    title            VARCHAR(255) NOT NULL,
    value            INTEGER,
    description      VARCHAR(1000),
    actors           VARCHAR(255),
    components       VARCHAR(255),
    gain_type        VARCHAR(255),
    difficulty       INTEGER,
    logo             VARCHAR(255) NOT NULL,
    background_image VARCHAR(255) NOT NULL,
    subtitle         VARCHAR(255),
    CONSTRAINT pk_card PRIMARY KEY (id)
);

CREATE TABLE favorite
(
    id       BIGINT                                NOT NULL,
    user_id  VARCHAR(255)                          NOT NULL,
    category VARCHAR(255) DEFAULT 'non rencontrée' NOT NULL,
    CONSTRAINT pk_favorite PRIMARY KEY (id)
);

CREATE TABLE game
(
    id              BIGINT                      NOT NULL,
    date            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    organisation_id BIGINT                      NOT NULL,
    CONSTRAINT pk_game PRIMARY KEY (id)
);

CREATE TABLE organisation
(
    id          BIGINT      NOT NULL,
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(256),
    is_public   BOOLEAN     NOT NULL,
    CONSTRAINT pk_organisation PRIMARY KEY (id)
);

ALTER TABLE organisation
    ADD CONSTRAINT uc_organisation_name UNIQUE (name);

ALTER TABLE favorite
    ADD CONSTRAINT FK_FAVORITE_ON_ID FOREIGN KEY (id) REFERENCES card (id);

ALTER TABLE game
    ADD CONSTRAINT FK_GAME_ON_ORGANISATION FOREIGN KEY (organisation_id) REFERENCES organisation (id);
