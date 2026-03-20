CREATE TABLE passports(
    id SERIAL PRIMARY KEY,
    seria INT,
    number INT
);

CREATE TABLE people(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    passport_id INT REFERENCES passports(id) UNIQUE
);

INSERT INTO passports(seria, number) VALUES (1111, 123456);
INSERT INTO passports(seria, number) VALUES (1112, 123457);
INSERT INTO passports(seria, number) VALUES (1113, 123458);

INSERT INTO people(name, passport_id) VALUES ('Ivan', 1);
INSERT INTO people(name, passport_id) VALUES ('Boris', 2);
INSERT INTO people(name, passport_id) VALUES ('Petr', 3);
INSERT INTO people(name) VALUES ('Vasya');
INSERT INTO people(name) VALUES ('Anya');

SELECT pp.name, p.seria, p.number
FROM people AS pp JOIN passports AS p ON pp.passport_id = p.id;

SELECT pp.name AS Имя, p.seria AS Серия, p.number AS Номер
FROM people AS pp JOIN passports AS p ON pp.passport_id = p.id;

SELECT pp.name AS "Имя владельца", p.seria AS Серия, p.number AS Номер
FROM people pp JOIN passports p ON pp.passport_id = p.id;