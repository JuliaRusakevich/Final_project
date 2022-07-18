ALTER TABLE user_info.users
    ADD COLUMN created_at TIMESTAMP;

ALTER TABLE user_info.users
    ADD COLUMN modified_at TIMESTAMP;

ALTER TABLE user_info.users
    ADD COLUMN created_by TEXT;

ALTER TABLE user_info.users
    ADD COLUMN modified_by TEXT;

ALTER TABLE user_info.users
    ADD COLUMN enabled BOOLEAN;

ALTER TABLE user_info.users DROP COLUMN created_by;
ALTER TABLE user_info.users DROP COLUMN modified_by;

