CREATE TABLE IF NOT EXISTS users(
    id SERIAL,
    username VARCHAR,
    email VARCHAR,
    password VARCHAR,
    name VARCHAR,
    surname VARCHAR,
    phone_number VARCHAR,
    role VARCHAR,
    is_enable BOOLEAN,

    PRIMARY KEY (id),
    UNIQUE (username),
    UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS product_types(
    id SERIAL,
    name VARCHAR,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products(
    id SERIAL,
    name VARCHAR,
    type_id INTEGER,
    price INTEGER,

    PRIMARY KEY (id),
    FOREIGN KEY (type_id) REFERENCES product_types(id)
);

CREATE TABLE IF NOT EXISTS product_specifications(
    id SERIAL,
    product_id INTEGER,
    key VARCHAR,
    value VARCHAR,

    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS carts(
    id SERIAL,
    user_id INTEGER,
    product_id INTEGER,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
