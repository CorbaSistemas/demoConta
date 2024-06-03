CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(12),
    password VARCHAR(12),
    roles VARCHAR(50)
);