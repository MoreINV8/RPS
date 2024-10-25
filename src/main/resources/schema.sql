CREATE TABLE customer
(
    id           CHAR(10),
    first_name   VARCHAR(20)        NOT NULL,
    last_name    VARCHAR(20)        NOT NULL,
    email        VARCHAR(50) UNIQUE NOT NULL,
    phone_number CHAR(10)           NOT NULL,
    address      VARCHAR(50)        NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE delivery
(
    id                 CHAR(10),
    customer_id        CHAR(10),
    created_date       DATE,
    delivered_date     DATE        NOT NULL,
    item_type          VARCHAR(20) NOT NULL,
    destination        VARCHAR(50) NOT NULL,
    sent_detail_status VARCHAR(5)  NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE,
    CHECK ( sent_detail_status IN ('TODO', 'FIN') )
);

CREATE TABLE employee
(
    id           CHAR(10),
    first_name   VARCHAR(20)        NOT NULL,
    last_name    VARCHAR(20)        NOT NULL,
    sex          VARCHAR(7)         NOT NULL,
    email        VARCHAR(50) UNIQUE NOT NULL,
    phone_number CHAR(10)           NOT NULL,
    role         VARCHAR(10)        NOT NULL,
    address      VARCHAR(50)        NOT NULL,
    password     VARCHAR(20)        NOT NULL,

    PRIMARY KEY (id),
    CHECK ( sex IN ('MALE', 'FEMALE') )
);

CREATE TABLE car
(
    car_registration VARCHAR(10),
    driver_id        CHAR(10),
    oil_type         VARCHAR(10) NOT NULL,
    finish_used      DATE,
    car_type         VARCHAR(10) NOT NULL,

    PRIMARY KEY (car_registration),
    FOREIGN KEY (driver_id) REFERENCES employee (id) ON DELETE CASCADE
);

CREATE TABLE notice
(
    id               CHAR(10),
    delivery_id      CHAR(10),
    driver_id        CHAR(10),
    car_registration VARCHAR(10),
    start_work_date  DATE       NOT NULL,
    complete_status  VARCHAR(5) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (delivery_id) REFERENCES delivery (id) ON DELETE CASCADE,
    FOREIGN KEY (driver_id) REFERENCES employee (id) ON DELETE CASCADE,
    FOREIGN KEY (car_registration) REFERENCES car (car_registration) ON DELETE CASCADE,
    CHECK ( complete_status IN ('INCOMPLETE', 'COMPLETE') )
);

CREATE TABLE product
(
    id          CHAR(10),
    notice_id   CHAR(10),
    delivery_id CHAR(10),
    item_count  INT         NOT NULL,
    item_detail VARCHAR(50) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (notice_id) REFERENCES notice (id) ON DELETE CASCADE,
    FOREIGN KEY (delivery_id) REFERENCES delivery (id) ON DELETE CASCADE
);

CREATE TABLE bill
(
    id           CHAR(10),
    delivery_id  CHAR(10),
    created_date DATE NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (delivery_id) REFERENCES delivery (id) ON DELETE CASCADE
);

CREATE TABLE route_problem
(
    latitude       INT,
    longitude      INT,
    reporter_id    CHAR(10),
    problem        VARCHAR(50),
    problem_detail VARCHAR(100),
    reported_date  DATE,

    PRIMARY KEY (latitude, longitude),
    FOREIGN KEY (reporter_id) REFERENCES employee (id) ON DELETE CASCADE
);