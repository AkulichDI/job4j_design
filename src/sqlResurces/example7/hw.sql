
CREATE TABLE car_bodies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE car_engines (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE car_transmissions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    body_id INT REFERENCES car_bodies(id),
    engine_id INT REFERENCES car_engines(id),
    transmission_id INT REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies (name) VALUES
('седан'),
('хэтчбек'),
('пикап'),
('универсал');

INSERT INTO car_engines (name) VALUES
('1.6L бензин'),
('2.0L бензин'),
('2.5L дизель'),
('3.0L бензин');

INSERT INTO car_transmissions (name) VALUES
('МКПП'),
('АКПП'),
('Робот');

INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
('Toyota Camry', 1, 2, 2),
('Honda Civic', 2, 1, 1),
('Ford Ranger', 3, 3, 1),
('Volkswagen Passat', 1, 2, 3),
('Skoda Octavia', 4, 1, 2);


SELECT
    cars.id AS id,
    cars.name AS car_name,
    body.name AS body_name,
    engine.name AS engine_name,
    trans.name AS transmission_name
FROM cars
LEFT JOIN car_bodies body ON cars.body_id = body.id
LEFT JOIN car_engines engine ON cars.engine_id = engine.id
LEFT JOIN car_transmissions trans ON cars.transmission_id = trans.id;

SELECT name AS unused_body
FROM car_bodies
WHERE id NOT IN (SELECT DISTINCT body_id FROM cars WHERE body_id IS NOT NULL);

SELECT name AS unused_engine
FROM car_engines
WHERE id NOT IN (SELECT DISTINCT engine_id FROM cars WHERE engine_id IS NOT NULL);


SELECT name AS unused_transmission
FROM car_transmissions
WHERE id NOT IN (SELECT DISTINCT transmission_id FROM cars WHERE transmission_id IS NOT NULL);



