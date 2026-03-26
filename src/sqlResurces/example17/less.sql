
CREATE TABLE users
(
    id bigint generated always as identity primary key,
    username text not null
);



INSERT INTO  users ( username )
SELECT 'person' || n
FROM generate_series(1, 10000) AS n;


CREATE INDEX people_username_index on users(username);

EXPLAIN analyze SELECT * FROM users ORDER BY username;


ANALYZE users;


SELECT * FROM pg_class WHERE relname = 'users';