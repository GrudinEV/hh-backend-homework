CREATE TABLE area
(
    id   bigint,
    name varchar,
    PRIMARY KEY (id)
);

CREATE TABLE employer
(
    id bigint,
    name varchar,
    date_create date,
    description varchar,
    area_id bigint,
    comment varchar,
    views_count int,
    PRIMARY KEY (id),
    FOREIGN KEY (area_id) REFERENCES area (id)
);