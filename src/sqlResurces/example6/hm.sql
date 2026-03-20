CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    department_id INT REFERENCES departments(id)
);

INSERT INTO departments(name) VALUES ('IT'), ('HR'), ('Finance'), ('Marketing');

INSERT INTO employees(name, department_id) VALUES ('Ivan', 1);
INSERT INTO employees(name, department_id) VALUES ('Petr', 1);
INSERT INTO employees(name, department_id) VALUES ('Olga', 2);
INSERT INTO employees(name) VALUES ('Stepan');


SELECT d.name, e.name
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id;


SELECT d.name, e.name
FROM departments d
RIGHT JOIN employees e ON d.id = e.department_id;


SELECT d.name, e.name
FROM departments d
FULL JOIN employees e ON d.id = e.department_id;

SELECT d.name, e.name
FROM departments d
CROSS JOIN employees e;

SELECT d.name
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id
WHERE e.id IS NULL;

SELECT e.name, d.name
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;

SELECT e.name, d.name
FROM departments d
RIGHT JOIN employees e ON d.id = e.department_id;


CREATE TABLE teens (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    gender VARCHAR(10)
);

INSERT INTO teens(name, gender) VALUES
('Ivan', 'male'),
('Petr', 'male'),
('Olga', 'female'),
('Anna', 'female');


SELECT t1.name, t2.name
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender <> t2.gender
AND t1.id < t2.id;