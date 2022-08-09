INSERT INTO security.users (uuid, mail, nick, password, status, created_by, modified_by, created_at, modified_at,
                            version)
VALUES ('98210a33-30d0-46dd-9579-14e93deae5aa',
        'admin',
        'admin',
        '$2a$10$w/qt2sBP//Jb2nxUzM9mBe6b2hsbA2HSaLomFNtj8EdVCitYDtyz.',
        'ACTIVATED',
        'admin',
        'admin',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        0);

INSERT INTO security.authorities (user_uuid, authority)
values ('98210a33-30d0-46dd-9579-14e93deae5aa', 'ROLE_USER');

INSERT INTO security.authorities (user_uuid, authority)
values ('98210a33-30d0-46dd-9579-14e93deae5aa', 'ROLE_ADMIN');
