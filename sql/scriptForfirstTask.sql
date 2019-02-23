create database pageParser;
--create tables
create table users(
	id serial primary key, 
	name varchar(100)
); 
create table roles(
	id serial primary key, 
	name varchar(200)
);
alter table users add column role_id int; 
alter table users add foreign key(role_id) references roles(id);
create table rules (
	id serial primary key, 
	descript varchar(2000)
);
create table rules_of_roles (
	id serial primary key, 
	id_role int references roles(id), 
	id_rule int references rules(id)
);
create table states (
	id serial primary key, 
	name varchar(200)
);
create table categories (
	id serial primary key, 
	name varchar(300)
);
create table items (
	id serial primary key, 
	descript varchar(500), 
	id_user int references users(id), 
	id_category int references categories(id), 
	id_state int references states(id)
); 
create table attachItem (
	id serial primary key, 
	url varchar(2000), 
	id_item int references items(id)
);
create table commentsItem (
	id serial primary key, 
	descript varchar(2000), 
	id_item int references items(id)
);
--insert
insert into roles (name) values ('admin'), ('user'), ('tester');
insert into users (name, role_id) values ('Vasya', 1), ('Petya', 2), ('Kolya', 2), ('Mitya', 3);
insert into rules (descript) values ('consume the product'), ('run the project'), ('search faults'), ('consult users');
insert into rules_of_roles (id_role, id_rule) values (1, 2), (1, 4), (2, 1), (3, 3), (3, 4);
insert into states (name) values ('run'), ('wait'), ('terminated'), ('completed');
insert into categories (name) values ('urgent'), ('usual'), ('trash');
insert into items (descript, id_user, id_category, id_state) values ('do something useful', 2, 2, 1), ('fix the problems', 3, 1, 4), ('view something', 2, 3, 1);
insert into attachItem (url, id_item) values ('cdcdjw00d', 3), ('dhdjka67', 3);
insert into commentsItem (descript, id_item) values ('text', 1), ('text...', 1);
