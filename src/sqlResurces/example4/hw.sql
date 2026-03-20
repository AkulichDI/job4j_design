CREATE TABLE devices (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price FLOAT
);

CREATE TABLE people (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE devices_people (
    id SERIAL PRIMARY KEY,
    device_id INT REFERENCES devices(id),
    people_id INT REFERENCES people(id)
);

INSERT INTO devices(name, price) VALUES ('Phone', 10000);
INSERT INTO devices(name, price) VALUES ('Laptop', 50000);
INSERT INTO devices(name, price) VALUES ('Tablet', 20000);
INSERT INTO devices(name, price) VALUES ('Headphones', 3000);

INSERT INTO people(name) VALUES ('Ivan');
INSERT INTO people(name) VALUES ('Petr');
INSERT INTO people(name) VALUES ('Olga');

INSERT INTO devices_people(device_id, people_id) VALUES (1, 1);
INSERT INTO devices_people(device_id, people_id) VALUES (2, 1);
INSERT INTO devices_people(device_id, people_id) VALUES (3, 2);
INSERT INTO devices_people(device_id, people_id) VALUES (4, 2);
INSERT INTO devices_people(device_id, people_id) VALUES (1, 3);

SELECT AVG(price) FROM devices;

SELECT p.name, AVG(d.price)
FROM devices_people dp
JOIN people p ON dp.people_id = p.id
JOIN devices d ON dp.device_id = d.id
GROUP BY p.name;

SELECT p.name, AVG(d.price)
FROM devices_people dp
JOIN people p ON dp.people_id = p.id
JOIN devices d ON dp.device_id = d.id
GROUP BY p.name
HAVING AVG(d.price) > 5000;