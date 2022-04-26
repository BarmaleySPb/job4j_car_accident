create table if not exists accident (
    id serial primary key,
    name varchar(2000),
    text varchar(2000),
    address varchar(2000),
    type_id integer references accident_type (id)
);

create table if not exists accident_type (
    id serial primary key,
    name varchar(100)
);

create table if not exists accident_rule (
    id serial primary key,
    name varchar(100)
);

create table if not exists accidents_rules (
    id serial primary key,
    accident_id integer references accident (id),
    rule_id  integer references accident_rule (id)
);


insert into accident_type (name) values
                                     ('Две машины'), ('Машина и человек'), ('Машина и велосипед');

insert into accident_rule (name) values
                                     ('Статья. 1'), ('Статья. 2'), ('Статья. 3');