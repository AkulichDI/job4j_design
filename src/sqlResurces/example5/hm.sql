CREATE TABLE type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type_id INT REFERENCES type(id),
    expired_date DATE,
    price FLOAT
);

INSERT INTO type(name) VALUES ('СЫР');
INSERT INTO type(name) VALUES ('МОЛОКО');
INSERT INTO type(name) VALUES ('МЯСО');
INSERT INTO type(name) VALUES ('МОРОЖЕНОЕ');

INSERT INTO product(name, type_id, expired_date, price) VALUES ('Сыр плавленный', 1, '2026-03-01', 200);
INSERT INTO product(name, type_id, expired_date, price) VALUES ('Сыр моцарелла', 1, '2026-04-01', 250);
INSERT INTO product(name, type_id, expired_date, price) VALUES ('Молоко 3.2%', 2, '2026-03-10', 100);
INSERT INTO product(name, type_id, expired_date, price) VALUES ('Мороженое ванильное', 4, '2026-05-01', 150);
INSERT INTO product(name, type_id, expired_date, price) VALUES ('Мороженое шоколадное', 4, '2026-05-10', 150);
INSERT INTO product(name, type_id, expired_date, price) VALUES ('Мясо говядина', 3, '2026-02-01', 300);

SELECT p.*
FROM product p
JOIN type t ON p.type_id = t.id
WHERE t.name = 'СЫР';

SELECT *
FROM product
WHERE name LIKE '%мороженое%';

SELECT *
FROM product
WHERE expired_date < CURRENT_DATE;

SELECT *
FROM product
WHERE price = (SELECT MAX(price) FROM product);

SELECT t.name, COUNT(p.id)
FROM type t
JOIN product p ON p.type_id = t.id
GROUP BY t.name;

SELECT p.*
FROM product p
JOIN type t ON p.type_id = t.id
WHERE t.name IN ('СЫР', 'МОЛОКО');

SELECT t.name, COUNT(p.id)
FROM type t
JOIN product p ON p.type_id = t.id
GROUP BY t.name
HAVING COUNT(p.id) < 10;

SELECT p.name, t.name
FROM product p
JOIN type t ON p.type_id = t.id;