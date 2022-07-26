alter table classifier.concert_category
    add column created_at timestamp;

alter table classifier.concert_category
    add column modified_at timestamp;

alter table classifier.concert_category
    add column created_by text;

alter table classifier.concert_category
    add column modified_by text;



alter table classifier.country
    add column created_at timestamp;

alter table classifier.country
    add column modified_at timestamp;

alter table classifier.country
    add column created_by text;

alter table classifier.country
    add column modified_by text;