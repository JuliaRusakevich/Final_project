CREATE DATABASE users;

CREATE SCHEMA IF NOT EXISTS user_info
    AUTHORIZATION postgres;

-- auto-generated definition
create table users
(
    uuid        uuid not null
        primary key,
    created_at  timestamp,
    created_by  varchar(255),
    modified_at timestamp,
    modified_by varchar(255),
    mail        varchar(255),
    nick        varchar(255),
    password    varchar(255),
    status      varchar(255)
);

alter table users
    owner to postgres;

-- auto-generated definition
create table authorities
(
    user_uuid uuid not null
        constraint fk2xdyll0ehxo3sod69opsswwrh
            references users,
    authority varchar(255)
);

alter table authorities
    owner to postgres;


