create database poster;

create schema if not exists poster
    authorization postgres;

create table poster.events
(
    uuid           uuid primary key,
    title          varchar(35),
    description    text,
    type           varchar(15),
    status         varchar(15),
    dt_event       timestamp,
    dt_end_of_sale timestamp,
    created_at     timestamp,
    modified_at    timestamp,
    created_by     text,
    modified_by    text,
    version        int
);

alter table poster.events
    owner to postgres;

create table concerts
(
    category uuid,
    uuid     uuid
        references poster.events
);

alter table poster.concerts
    owner to postgres;

create table films
(
    country      uuid,
    release_year integer,
    release_date date,
    duration     integer,
    uuid         uuid
        references poster.events
);

alter table films
    owner to postgres;