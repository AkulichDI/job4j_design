create table companies (

    id int primary key,
    city text

);

CREATE TABLE goods (
    id         INT PRIMARY KEY,
    name       TEXT,
    company_id INT REFERENCES companies (id),
    price      INT
);


CREATE TABLE sales_managers
(
    id          INT PRIMARY KEY,
    last_name   TEXT,
    first_name  TEXT,
    company_id  INT REFERENCES companies (id),
    manager_fee INT
);

create table managers (
    id int primary key,
    company_id int references companies (id)
);

insert into companies values
(1, 'Москва'),
(2, 'Нью-Йорк'),
(3, 'Мюнхен');

insert into goods
values (1, 'Небольшая квартира', 3, 5000),
       (2, 'Квартира в центре', 1, 4500),
       (3, 'Квартира у метро', 1, 3200),
       (4, 'Лофт', 2, 6700),
       (5, 'Загородный дом', 2, 9800);



INSERT INTO sales_managers
VALUES (1, 'Доу', 'Джон', 2, 2250),
       (2, 'Грубер', 'Ганс', 3, 3120),
       (3, 'Смит', 'Сара', 2, 1640),
       (4, 'Иванов', 'Иван', 1, 4500),
       (5, 'Купер', 'Грета', 3, 2130);



INSERT INTO managers
VALUES (1, 2),
       (2, 3),
       (4, 1);





SELECT * FROM sales_managers
WHERE manager_fee > (SELECT AVG(manager_fee) FROM sales_managers);


SELECT
name as "Наименование",
price as "Цена",
(select avg(price) from goods) as "Средняя стоимость"
from
goods;
