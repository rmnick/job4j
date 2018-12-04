
/*
--create
create table engine(
	id serial primary key, 
	name varchar(500), 
	number int
);
create table transmission(
	id serial primary key, 
	name varchar(500), 
	number int
);
create table body_car(
	id serial primary key, 
	name varchar(500), 
	number int
);
create table cars(
	id serial primary key, 
	name varchar(500), 
	number int, 
	id_engine int references engine(id), 
	id_transmission int references transmission(id), 
	id_body_car int references body_car(id)
);
--insert
insert into engine (name, number) values ('BMW M56', 5), 
('Mercedes OM602', 4), 
('Toyota 3S-FE', 2), 
('Opel 20ne', 3);
insert into transmission (name, number) values ('6hp21', 3), 
('W7X550', 4), 
('TB60sn', 2), 
('A5GF1', 2);
insert into body_car (name, number) values ('sedan', 3), 
('hatchback', 2), 
('crossover', 1), 
('cabriolet', 1);
insert into cars (name, number, id_engine, id_transmission, id_body_car) values ('BMW M5', 1, 1, 1, 1), 
('Mercedes S', 2, 2, 2, 1),
('Mercedes CL', 1, 2, 2, 3), 
('TOYOTA AURIS', 2, 3, 3, 2);
--select
select c.id, c.name, e.name, t.name, b.name from cars as c 
left outer join engine as e on c.id_engine = e.id 
left outer join transmission as t on c.id_transmission = t.id
left outer join body_car as b on c.id_body_car = b.id;

select e.name from cars as c right outer join engine as e on c.id_engine = e.id where c.id is null; 
select t.name from cars as c right outer join transmission as t on c.id_transmission = t.id where c.id is null; 
select b.name from cars as c right outer join body_car as b on c.id_body_car = b.id where c.id is null; 
*/