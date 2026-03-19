insert into roles (name) values ('Reader');
insert into rules (name) values ('Read');
insert into users (name, role_id) values ('Oleg', 1);
insert into role_rules (id_role, id_rule) values (1, 1);

insert into categories (name) values ('Clean');
insert into states (name) values ('Ready');
insert into items (id_user, id_category, id_state) values (1, 1, 1);
insert into comments (id_item, name) values (1, 'Не знаю');
insert into attachs (id_item, name) values (1, 'Test');