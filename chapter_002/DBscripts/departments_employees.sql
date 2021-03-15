create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('D1');
insert into departments(name) values ('D2');
insert into departments(name) values ('D3');
insert into departments(name) values ('D4');
insert into departments(name) values ('D5');
insert into departments(name) values ('D6');

insert into employees(name, department_id) values ('E1', 1);
insert into employees(name, department_id) values ('E2', 1);
insert into employees(name, department_id) values ('E3', 1);
insert into employees(name, department_id) values ('E4', 3);
insert into employees(name, department_id) values ('E5', 3);
insert into employees(name, department_id) values ('E6', 3);
insert into employees(name, department_id) values ('E7', 3);
insert into employees(name, department_id) values ('E8', 4);
insert into employees(name, department_id) values ('E9', 4);
insert into employees(name, department_id) values ('E10', 4);
insert into employees(name, department_id) values ('E11', 4);
insert into employees(name, department_id) values ('E12', 5);
insert into employees(name, department_id) values ('E13', 5);
insert into employees(name, department_id) values ('E14', 5);
insert into employees(name, department_id) values ('E15', 5);