create database classifier;

create schema if not exists classifier
    authorization postgres;

create table classifier.concert_category
(
    uuid        uuid        primary key ,
    title       varchar(25) not null,
    created_at  timestamp,
    modified_at timestamp,
    created_by  text,
    modified_by text
);

alter table classifier.concert_category
    owner to postgres;


create table classifier.country
(
    uuid        uuid        primary key ,
    title       varchar(3)  unique,
    description varchar(35) not null,
    created_at  timestamp,
    modified_at timestamp,
    created_by  text,
    modified_by text
);

alter table classifier.country
    owner to postgres;


