create table if not exists accounts (id serial primary key, name varchar(150) not null,
phone varchar(50) not null);

create table if not exists hall (id smallint primary key not null,
row smallint not null,
number smallint not null,
booked boolean not null,
id_account int references accounts(id),
price int not null);

insert into hall values (11, 1, 1, false, null, 100), (12, 1, 2, false, null, 100), (13, 1, 3, false, null, 100),
(21, 2, 1, false, null, 100), (22, 2, 2, false, null, 100), (23, 2, 3, false, null, 100),
(31, 3, 1, false, null, 100), (32, 3, 2, false, null, 100), (33, 3, 3, false, null, 100);