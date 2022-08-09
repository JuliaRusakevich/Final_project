create database users;

create schema if not exists security
    authorization postgres;

create table security.users
(
    uuid        uuid primary key,
    mail        varchar(255) unique,
    nick        varchar(255),
    password    varchar(255),
    status      varchar(255),
    created_by  varchar(255),
    modified_by varchar(255),
    created_at  timestamp(3),
    modified_at timestamp(3),
    version     integer
);

alter table security.users
    owner to postgres;


create table security.authorities
(
    user_uuid uuid not null
        constraint fk2xdyll0ehxo3sod69opsswwrh
            references security.users,
    authority varchar(255)
);

alter table security.authorities
    owner to postgres;



