/*CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);*/

/*CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
);*/
--select * from company;
--insert into company (id, name) values (1, 'a'), (2, 'b'), (3, 'c'), (4, 'd'), (5, 'f'), (6, 'g');
--insert into person (id, name, company_id) values (1, 'bob', 1), (2, 'john', 3), (3, 'bill', 2), (4, 'nick', 5), (5, 'sella', 5), (6, 'djosh', 2), (7, 'gim', 4), (8, 'pol', 6), (9, 'guy', 1), (10, 'suzan', 5);
--select * from person;
--select p.name, c.name from person p inner join company c on c.id = p.company_id where c.id != 5;
--select c.name, count(*) as conter FROM company c inner join person p on p.company_id = c.id group by c.name
--select max(conter) from (select count(*) as conter FROM company c inner join person p on p.company_id = c.id group by c.name) as result;
--delete from person;