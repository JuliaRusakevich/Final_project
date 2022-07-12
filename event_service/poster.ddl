CREATE DATABASE poster;

CREATE SCHEMA IF NOT EXISTS poster
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS poster.events
(
    uuid           uuid PRIMARY KEY,
    dt_create      TIMESTAMP   NOT NULL,
    dt_update      TIMESTAMP   NOT NULL,
    title          VARCHAR(35) NOT NULL,
    description    TEXT        NOT NULL,
    dt_event       TIMESTAMP   NOT NULL,
    dt_end_of_sale TIMESTAMP   NOT NULL,
    event_type     VARCHAR(15) NOT NULL,
    event_status   VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS poster.films
(
    uuid_country VARCHAR(100) NOT NULL,
    release_year INTEGER      NOT NULL,
    release_date DATE         NOT NULL,
    duration     INTEGER      NOT NULL,
    uuid         uuid REFERENCES poster.events
);

CREATE TABLE IF NOT EXISTS poster.concerts
(
    uuid_category VARCHAR(100) NOT NULL,
    uuid          uuid REFERENCES poster.events

);

