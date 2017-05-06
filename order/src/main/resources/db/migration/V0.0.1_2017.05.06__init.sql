
CREATE TABLE orders (
    id SERIAL,
    number VARCHAR(255) NOT NULL,
    created_at DATE,
    customer VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE item (
    id SERIAL,
    order_id BIGINT NOT NULL REFERENCES orders (id),
    name VARCHAR(255) NOT NULL,
    quantity BIGINT NOT NULL,
    unit_price INTEGER NOT NULL,
    PRIMARY KEY(id)
);