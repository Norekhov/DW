-- liquibase formated sql
-- changeset norekhov:1
CREATE TABLE users
(
    id         INT          NOT NULL PRIMARY KEY,
    email      VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    phone      VARCHAR(255) NOT NULL UNIQUE,
    role       VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL
);
CREATE TABLE ad
(
    pk      INT          NOT NULL PRIMARY KEY,
    price   INT          NOT NULL,
    title   VARCHAR(255) NOT NULL,
    adText  TEXT         NOT NULL,
    user_id INT REFERENCES user (id)
);
CREATE TABLE comment
(
    pk        INT  NOT NULL PRIMARY KEY,
    text      TEXT NOT NULL,
    createdAt INT  NOT NULL,
    user_id   INT REFERENCES user (id),
    ad_id     INT REFERENCES ad (pk)
);
CREATE TABLE userAvatar
(
    id        INT          NOT NULL PRIMARY KEY,
    filePath  VARCHAR(255) NOT NULL,
    fileSize  BIGINT       NOT NULL,
    mediaType VARCHAR(255) NOT NULL,
    data      BYTEA        NOT NULL,
    userZ_id  INT REFERENCES user (id)
);
CREATE TABLE adPicture
(
    id        INT          NOT NULL PRIMARY KEY,
    filePath  VARCHAR(255) NOT NULL,
    fileSize  BIGINT       NOT NULL,
    mediaType VARCHAR(255) NOT NULL,
    data      BYTEA        NOT NULL,
    ad_id     INT REFERENCES ad (pk)
);