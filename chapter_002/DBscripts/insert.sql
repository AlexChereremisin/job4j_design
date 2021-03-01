insert into role (role) values ('role1');
insert into role (role) values ('role2');
insert into role (role) values ('role3');

insert into system_user (name, role_id) values ('user1', 1);
insert into system_user (name, role_id) values ('user2', 1);
insert into system_user (name, role_id) values ('user3', 2);
insert into system_user (name, role_id) values ('user4', 3);
insert into system_user (name, role_id) values ('user5', 3);
insert into system_user (name, role_id) values ('user6', 3);

insert into rules (rule) values ('rules1');
insert into rules (rule) values ('rules2');
insert into rules (rule) values ('rules3');

insert into role_rules (role_id, rules_id) values (1, 1);
insert into role_rules (role_id, rules_id) values (1, 2);
insert into role_rules (role_id, rules_id) values (2, 1);
insert into role_rules (role_id, rules_id) values (2, 2);
insert into role_rules (role_id, rules_id) values (2, 3);
insert into role_rules (role_id, rules_id) values (3, 3);
insert into role_rules (role_id, rules_id) values (3, 1);

insert into state (state) values ('State1');
insert into state (state) values ('State2');
insert into state (state) values ('State3');

insert into category (category) values ('Category1');
insert into category (category) values ('Category2');
insert into category (category) values ('Category3');

insert into item (item, user_id, category_id, state_id) values ('Item1', 1, 2, 3);
insert into item (item, user_id, category_id, state_id) values ('Item2', 2, 2, 2);
insert into item (item, user_id, category_id, state_id) values ('Item3', 3, 1, 1);
insert into item (item, user_id, category_id, state_id) values ('Item4', 2, 1, 2);
insert into item (item, user_id, category_id, state_id) values ('Item5', 3, 3, 3);

insert into comments (comments, item_id) values ('Comment1', 1);
insert into comments (comments, item_id) values ('Comment2', 1);
insert into comments (comments, item_id) values ('Comment3', 1);
insert into comments (comments, item_id) values ('Comment4', 2);
insert into comments (comments, item_id) values ('Comment5', 3);
insert into comments (comments, item_id) values ('Comment6', 3);

insert into attaches (attaches, item_id) values ('{"path1", "path2"}', 1);
insert into attaches (attaches, item_id) values ('{"path1", "path2", "path3"}', 1);
insert into attaches (attaches, item_id) values ('{"path1"}', 2);
insert into attaches (attaches, item_id) values ('{"path1", "path2", "path3", "path4"}', 2);