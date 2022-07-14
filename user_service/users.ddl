CREATE DATABASE users;

CREATE SCHEMA IF NOT EXISTS user_info
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS user_info.users
(
    uuid      uuid        NOT NULL,
    dt_create TIMESTAMP   NOT NULL,
    dt_update TIMESTAMP   NOT NULL,
    mail      VARCHAR(35) NOT NULL UNIQUE,
    nick      VARCHAR(35) NOT NULL,
    role      VARCHAR(10) NOT NULL,
    status    VARCHAR(20) NOT NULL,
    password  VARCHAR(35) NOT NULL
);