CREATE TABLE users
(
    id         INT          NOT NULL PRIMARY KEY,
    username   VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    phone      VARCHAR(255) NOT NULL UNIQUE,
    avatar     VARCHAR(255),
    enabled    integer default 1 NOT NULL
);
CREATE TABLE ad
(
    pk          INT          NOT NULL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    adText      TEXT         NOT NULL,
    price       INT          NOT NULL,
    user_id     INT REFERENCES users (id),
    image_url   VARCHAR(255) NOT NULL
);
CREATE TABLE comment
(
    pk        INT    NOT NULL PRIMARY KEY,
    text      TEXT   NOT NULL,
    createdAt BIGINT NOT NULL,
    user_id   INT REFERENCES users (id),
    ad_id     INT REFERENCES ad (pk)
);

-- INSERT INTO users (id, username, first_name, last_name, password, phone, role) VALUES
-- (1, 'admin@mail.ru', 'admin', 'adminov', '$2a$10$PHqLP4.5DV8NXkToIWpaKehb2rKIaSaxv74hAmrBifkzWBTqJNkyS', '+7(912)345-67-89', 'ADMIN'),
-- (2, 'user@mail.ru', 'user', 'userov', '$2a$10$4eG1.GBPawmSx7fuMwQOE.hz7zVjr.AERz7KSCt0PuQg7iHRVpKh2', '+7(922)345-67-89', 'USER');