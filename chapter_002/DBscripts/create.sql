create table role(
    id serial primary key,
    role varchar(255)
);

create table rules(
    id serial primary key,
    rule text
);

create table role_rules(
    id serial primary key,
    role_id int references role(id),
    rules_id int references rules(id)
);

create table system_user(
   id serial primary key,
   name varchar(255),
   role_id int references role(id)
);

create type STATES as ENUM ('State1', 'State2', 'State3');

create table state(
    id serial primary key,
    state STATES
);

create type CATEGORIES as ENUM ('Category1', 'Category2', 'Category3');

create table category(
    id serial primary key,
    category CATEGORIES
);

create table item(
    id serial primary key,
    item text,
    user_id int references system_user(id),
    category_id int references category(id),
    state_id int references state(id)
);

create table comments(
    id serial primary key,
    comments text,
    item_id int references item(id)
);

create table attaches
(
    id serial primary key,
    attaches text[],
    item_id int references item(id)
);