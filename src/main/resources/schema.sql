CREATE TABLE customer
(
    id           CHAR(10),
    first_name   VARCHAR(50) NOT NULL,
    last_name    VARCHAR(50) NOT NULL,
    email        VARCHAR(50) NOT NULL,
    phone_number CHAR(10)    NOT NULL,
    address      VARCHAR(50) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE delivery
(
    id                 CHAR(10),
    customer_id        CHAR(10)    NOT NULL,
    created_date       DATE,
    delivered_date     DATE        NOT NULL,
    item_type          VARCHAR(50) NOT NULL,
    destination        VARCHAR(50) NOT NULL,
    sent_detail_status VARCHAR(5)  NOT NULL,
    all_product_count  INT,

    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE,
    CHECK ( sent_detail_status IN ('TODO', 'FIN') )
);

CREATE TABLE employee
(
    id           CHAR(10),
    first_name   VARCHAR(50)        NOT NULL,
    last_name    VARCHAR(50)        NOT NULL,
    sex          VARCHAR(7)         NOT NULL,
    email        VARCHAR(50) UNIQUE NOT NULL,
    phone_number CHAR(10)           NOT NULL,
    role         VARCHAR(15)        NOT NULL,
    address      VARCHAR(50)        NOT NULL,
    password     VARCHAR(20)        NOT NULL,

    PRIMARY KEY (id),
    CHECK ( role IN ('ADMIN', 'HR', 'SALE_MANAGER', 'MANAGER', 'DRIVER') ),
    CHECK ( sex IN ('MALE', 'FEMALE') )
);

CREATE TABLE car
(
    car_registration VARCHAR(50),
    driver_id        CHAR(10)    NOT NULL,
    oil_type         VARCHAR(20) NOT NULL,
    finish_used      DATETIME,
    car_type         VARCHAR(50) NOT NULL,

    PRIMARY KEY (car_registration),
    FOREIGN KEY (driver_id) REFERENCES employee (id) ON DELETE CASCADE,

    CHECK (oil_type IN ('แก๊สโซฮอล์ 91', 'แก๊สโซฮอล์ 95', 'แก๊สโซฮอล์ E20', 'แก๊สโซฮอล์ E85',
                        'ดีเซล B7', 'ดีเซล B10', 'ดีเซล B20', 'ดีเซลพรีเมี่ยม')),
    CHECK (car_type IN ('6 ล้อ', '6 ล้อสไลด์วางถาด', 'รถสไลด์ 10 ล้อ',
                        '10 ล้อสะพานท้ายติดตั้งวินซ์', '10 ล้อกระบะคาร์โก้ติดตั้งเครน 5 ตัน',
                        'โลเบท 8 เพลา', 'โลเบท 5 เพลา', 'โลเบท 6 เพลา', 'โลเบท 4 เพลา',
                        'โลเบท 3 เพลา', 'ดอลลี่'))
);

CREATE TABLE notice
(
    id               CHAR(10),
    delivery_id      CHAR(10)    NOT NULL,
    driver_id        CHAR(10)    NOT NULL,
    car_registration VARCHAR(50) NOT NULL,
    start_work_date  DATE        NOT NULL,
    complete_status  VARCHAR(20) NOT NULL,

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
    delivery_id CHAR(10)    NOT NULL,
    item_count  INT         NOT NULL,
    item_detail VARCHAR(50) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (notice_id) REFERENCES notice (id) ON DELETE CASCADE,
    FOREIGN KEY (delivery_id) REFERENCES delivery (id) ON DELETE CASCADE
);

CREATE TABLE bill
(
    id           CHAR(10),
    delivery_id  CHAR(10) NOT NULL,
    created_date DATE     NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (delivery_id) REFERENCES delivery (id) ON DELETE CASCADE
);

CREATE TABLE route_problem
(
    route_problem_id VARCHAR(10),
    province         VARCHAR(50)  NOT NULL,
    district         VARCHAR(50)  NOT NULL,
    road_name        VARCHAR(50)  NOT NULL,
    reporter_id      CHAR(10)     NOT NULL,
    problem_topic    VARCHAR(100) NOT NULL,
    problem_detail   VARCHAR(250) NOT NULL,
    reported_date    DATE         NOT NULL,

    PRIMARY KEY (route_problem_id),
    FOREIGN KEY (reporter_id) REFERENCES employee (id) ON DELETE CASCADE
);

CREATE TABLE counter
(
    table_name VARCHAR(20),
    counter    int,

    PRIMARY KEY (table_name)
);