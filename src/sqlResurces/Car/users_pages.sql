/* one-to-one */
create table pages (
	id serial primary key,
	name varchar(200)
);

create table users (
	id serial primary key,
	name varchar(100),
	id_page int references pages(id) unique
);

/* many-to-one */
create table pages (
	id serial primary key,
	name varchar(200)
);

create table users (
	id serial primary key,
	name varchar(100),
	id_page int references pages(id)
);

/* many-to-many */

create table pages (
	id serial primary key,
	name varchar(200)
);

create table users (
	id serial primary key,
	name varchar(100)
);

create table users_pages (
	id serial primary key,
	page_name varchar(100),
	id_page int references pages(id),
	id_user int references users(id)
);

