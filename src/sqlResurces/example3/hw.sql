CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    department_id INT REFERENCES departments(id)
);

INSERT INTO departments(name) VALUES ('IT');
INSERT INTO departments(name) VALUES ('HR');
INSERT INTO departments(name) VALUES ('Finance');

INSERT INTO employees(name, department_id) VALUES ('Ivan', 1);
INSERT INTO employees(name, department_id) VALUES ('Petr', 1);
INSERT INTO employees(name, department_id) VALUES ('Olga', 2);
INSERT INTO employees(name, department_id) VALUES ('Maria', 3);
INSERT INTO employees(name) VALUES ('Stepan');


select  dep.name as "Сотрудник", empl.name as "Департамент" from employees empl
join departments dep on empl.department_id = dep.id;