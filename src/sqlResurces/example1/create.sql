create table roles (
	id serial PRIMARY KEY, 
	name varchar(255)
	
);
create table users (
    id serial PRIMARY KEY,
	name varchar(255),
	role_id int REFERENCES roles(id) 
);



create table rules (
	id serial PRIMARY KEY,
	name text
);

create table role_rules(
	id serial 	PRIMARY KEY,
	id_role int REFERENCES roles(id),
	id_rule int REFERENCES rules(id)
);

create table categories(
	id serial PRIMARY KEY,
	name text
);
create table states (
	id serial PRIMARY KEY,
	name text
);

create table items (
	id serial PRIMARY KEY,
	id_user int REFERENCES users(id),
	id_category int REFERENCES categories(id),
	id_state int REFERENCES states(id)
);
create table comments(
	id serial PRIMARY KEY,
	id_item int REFERENCES items(id),
	name text
);

create table attachs (
	id serial PRIMARY KEY,
	id_item int REFERENCES items(id),
	name text 
);





