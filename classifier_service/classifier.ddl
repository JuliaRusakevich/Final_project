CREATE DATABASE classifier;

CREATE SCHEMA IF NOT EXISTS classifier
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS classifier.country
(
    uuid        uuid      NOT NULL,
    dt_create   TIMESTAMP NOT NULL,
    dt_update   TIMESTAMP NOT NULL,
    title       VARCHAR(3) NOT NULL,
    description VARCHAR(35) NOT NULL
);
