--create and insert example
/*
create database example;
create table types (id serial primary key, name varchar(500));
create table products (id serial primary key, name varchar(500), id_type int references types(id), expired_date date, price numeric, number int);
insert into types (name) values ('milk'), ('cheese'), ('icecreame'), ('liquor'), ('meat');
select * from types;
insert into products (name, id_type, expired_date, price, number) values 
('milk 3.2%', 1, '2018-12-10', 50.60, 20),
('milk 2.3%', 1, '2018-12-10', 40, 30),
('soybean milk', 1, '2019-01-10', 60, 5),
('parmesan', 2, '2019-04-20', 200, 10),
('brie', 2, '2019-05-20', 500, 4),
('chocolate icecreame', 3, '2019-10-10', 100, 20),
('strawberry icecreame', 3, '2019-10-10', 120, 30),
('wheeskey', 4, null, 1000, 9),
('vodka', 4, null, 500, 15),
('beef', 5, '2019-01-10', 400, 2);
*/
--select query
--1
--select p.name, p.price, t.name from products as p inner join types as t on t.id = p.id_type where t.name = 'cheese';
--2
--select * from products where name like ('%icecream%');
--3
--select * from products where extract(month from expired_date) = extract(month from (current_date + integer '30'));
--4
--select * from products where price = (select max(price) from products);
--5
--select count(p.id) from products as p inner join types as t on t.id = p.id_type where t.name = 'cheese';
--select sum(p.number) from products as p inner join types as t on t.id = p.id_type where t.name = 'milk';
--6
--select p.name, p.price, t.name from products as p inner join types as t on t.id = p.id_type where t.name = 'cheese' or t.name = 'milk';
--7
--select t.name from types as t inner join products as p on t.id = p.id_type where p.number > 10;
--8
--select p.id, p.name, p.price, p.expired_date, t.name from products as p inner join types as t on t.id = p.id_type;
