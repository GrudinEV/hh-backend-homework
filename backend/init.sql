CREATE TABLE area
(
    id   bigint,
    name varchar not null,
    PRIMARY KEY (id)
);

CREATE TABLE employer
(
    id bigint,
    name varchar not null,
    date_create date not null,
    description varchar,
    area_id bigint not null,
    comment varchar,
    views_count int not null,
    PRIMARY KEY (id),
    FOREIGN KEY (area_id) REFERENCES area (id)
);

CREATE TABLE vacancy
(
    id bigint,
    name varchar not null,
    date_create date not null,
    area_id bigint not null,
    salary_from int,
    salary_to int,
    salary_currency varchar,
    salary_gross boolean,
    created_at timestamp,
    employer_id bigint not null,
    comment varchar,
    views_count int not null,
    PRIMARY KEY (id),
    FOREIGN KEY (area_id) REFERENCES area (id),
    FOREIGN KEY (employer_id) REFERENCES employer (id)
);
