CREATE TABLE accident (
  id serial primary key,
  name varchar(2000)
);

create table accident_type (
    id serial primary key,
    name varchar(100)
);

create table accident_rule (
    id serial primary key,
    name varchar(100)
);

insert into accident_type (name) values
                                     ('Две машины'), ('Машина и человек'), ('Машина и велосипед');

insert into accident_rule (name) values
                                     ('Статья. 1'), ('Статья. 2'), ('Статья. 3');