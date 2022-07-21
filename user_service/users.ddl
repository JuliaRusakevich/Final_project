CREATE DATABASE users;

CREATE SCHEMA IF NOT EXISTS user_info
    AUTHORIZATION postgres;

-- auto-generated definition
create table user_info.users
(
    uuid        uuid primary key,
    dt_create   timestamp,
    dt_update   timestamp,
    username    text not null,
    nick        text,
    status      varchar(20),
    password    text not null,
    enabled     boolean,
    created_at  timestamp,
    modified_at timestamp,
    created_by  text,
    modified_by text
);

alter table user_info.users
    owner to postgres;


-- auto-generated definition
create table user_info.authorities
(
    id        bigserial primary key,
    authority text
);

alter table user_info.authorities
    owner to postgres;

create table user_info.users_authorities
(
    users_uuid   uuid    not null
        constraint fk2cmfwo8tbjcpmltse0rh5ir0t
            references users,
    authority_id integer not null
        constraint fkdsfxx5g8x8mnxne1fe0yxhjhq
            references authorities,
    primary key (users_uuid, authority_id)
);

alter table user_info.users_authorities
    owner to postgres;


