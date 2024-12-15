DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS ad;
DROP TABLE IF EXISTS users;
create sequence ad_seq
    increment by 1;

create sequence comment_seq
    increment by 1;

create sequence users_seq
    increment by 1;

create table if not exists users
(
    enabled    integer,
    user_id    integer not null primary key,
    avatar     varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255),
    phone      varchar(255),
    role       varchar(255) default 'USER',
    username   varchar(255)
);
create table if not exists ad
(
    ad_pk     integer not null
        primary key,
    price     integer,
    user_id   integer
        constraint fktofr7l4mo2aajd4mm1k7n93a6
            references users,
    ad_text   varchar(255),
    image_url varchar(255),
    title     varchar(255)
);
create table if not exists comment
(
    ad_pk      integer
        constraint fkc5at2qnh29gd8s96s19qc63wu
            references ad,
    comment_pk integer not null
        primary key,
    user_id    integer
        constraint fkqm52p1v3o13hy268he0wcngr5
            references users,
    created_at bigint,
    text       varchar(255)
);