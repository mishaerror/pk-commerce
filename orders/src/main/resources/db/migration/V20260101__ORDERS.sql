CREATE SEQUENCE order_ref_seq
    INCREMENT 5
START 100;

CREATE TABLE ORDERS
(
    id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ref BIGINT UNIQUE,
    state VARCHAR(100) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    address_line_one VARCHAR(200),
    address_line_two VARCHAR(100),
    address_postal_code VARCHAR(10),
    address_city VARCHAR(100),
    customer_name VARCHAR(100),
    customer_email VARCHAR(100),
    customer_phone VARCHAR(50),
    merchant_ref BIGINT NOT NULL,
    count INTEGER NOT NULL
);