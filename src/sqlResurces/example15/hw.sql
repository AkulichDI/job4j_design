create table customers
(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);


insert into customers values
(1,'Дмитрий', 'Иванов', 60, 'Россия'),
(2,'Олег', 'Иванов', 12, 'Россия'),
(3,'Иван', 'Иванов', 10, 'Россия');


select
*
from
customers
where
id = (select id from customers order by age asc limit 1 );


create table orders
(
    id serial primary key,
    amount int,
    customer_id int references customers (id)
);



select * from customers;


insert into orders values
(1, 200, 1),
(2, 400, 2),
(3, 600, 3),
(4, 800, 2);


insert into customers values (4, 'Роман', 'Иванович', 25, 'Россия');


select
*
from
customers
where
id not in (select distinct customer_id from orders )

