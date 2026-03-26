create table  customers (
    first_name text,
    last_name text,
    status text
);


create table employees
(
    first_name text,
    last_name text,
    emp_status text

);


INSERT INTO customers
VALUES ('Иван', 'Иванов', 'Active'),
       ('Петр', 'Сергеев', 'Active'),
       ('Ирина', 'Бросова', 'Active'),
       ('Анна', 'Опушкина', 'Active'),
       ('Потап', 'Потапов', 'Passive');

INSERT INTO employees
VALUES ('Кристина', 'Позова', 'Current'),
       ('Михаил', 'Кругов', 'Current'),
       ('Анна', 'Опушкина', 'Current'),
       ('Иван', 'Иванов', 'Current'),
       ('Сергей', 'Петров', 'Current');




select first_name, last_name from employees
union
select first_name, last_name from customers;