CREATE TABLE customer
(
    id           CHAR(10),
    first_name   VARCHAR(20)        NOT NULL,
    last_name    VARCHAR(20)        NOT NULL,
    email        VARCHAR(20) UNIQUE NOT NULL,
    phone_number CHAR(10)           NOT NULL,
    address      VARCHAR(50)        NOT NULL,

    PRIMARY KEY (id)
);