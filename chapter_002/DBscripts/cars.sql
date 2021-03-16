create table body(
    id serial primary key,
    name varchar(255)
);

create table engine(
    id serial primary key,
    name varchar(255)
) ;

create table transmission(
    id serial primary key,
    name varchar(255)
);

create table car(
    id serial primary key,
    name varchar(255),
    body_id int references body(id),
    engine_id int references engine(id),
    transmission_id int references transmission(id)
);

insert into body(name) values ('b1');
insert into body(name) values ('b2');
insert into body(name) values ('b3');
insert into body(name) values ('b4');
insert into body(name) values ('b5');

insert into engine(name) values ('e1');
insert into engine(name) values ('e2');
insert into engine(name) values ('e3');
insert into engine(name) values ('e4');
insert into engine(name) values ('e5');
insert into engine(name) values ('e6');

insert into transmission(name) values ('t1');
insert into transmission(name) values ('t2');
insert into transmission(name) values ('t3');

insert into car(name, body_id, engine_id, transmission_id) values ('c1', 1, 1, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('c2', 1, 2, 2);
insert into car(name, body_id, engine_id, transmission_id) values ('c3', 1, 3, 2);
insert into car(name, body_id, engine_id, transmission_id) values ('c4', 2, 3, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('c5', 2, 4, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('c6', 2, 1, 2);
insert into car(name, body_id, engine_id, transmission_id) values ('c7', 3, 4, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('c8', 3, 1, 2);
insert into car(name, body_id, engine_id, transmission_id) values ('c9', 3, 1, 2);
insert into car(name, body_id, engine_id, transmission_id) values ('c10', 4, 2, 2);