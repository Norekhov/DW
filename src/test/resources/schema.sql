DROP TABLE IF EXISTS comment cascade;
DROP TABLE IF EXISTS ad cascade;
DROP TABLE IF EXISTS users cascade;
DROP SEQUENCE IF EXISTS ad_seq;
DROP SEQUENCE IF EXISTS comment_seq;
DROP SEQUENCE IF EXISTS users_seq;
create sequence ad_seq increment by 1;

create sequence comment_seq increment by 1;

create sequence users_seq increment by 1;

create table if not exists users
(
    user_id    INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username   varchar(255),
    password   varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    phone      varchar(255),
    role       varchar(255) default 'USER',
    avatar     varchar(255),
    enabled    integer
);
create table if not exists ad
(
    ad_id     INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    price     integer,
    title     varchar(255),
    ad_text   varchar(255),
    image_url varchar(255),
    user_id   integer,
        foreign key (user_id) references users(user_id)
);
create table if not exists comment
(
    comment_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    created_at bigint,
    text       varchar(255),
    ad_id      integer,
        foreign key (ad_id) references ad(ad_id),
    user_id    integer,
        foreign key (user_id) references users(user_id)
);