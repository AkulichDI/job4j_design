BEGIN;

INSERT INTO products(name, producer, count, price)
VALUES ('product_5', 'producer_5', 17, 45);

SAVEPOINT sp1;

DELETE FROM products WHERE price = 115;

SAVEPOINT sp2;

UPDATE products SET price = 75 WHERE name = 'product_1';