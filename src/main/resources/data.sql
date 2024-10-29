INSERT INTO customer (id, first_name, last_name, email, phone_number, address)
VALUES ('c000000001', 'M', 'N', 'alphabet@gmail.com', '0123456789', 'Paris'),
       ('c000000002', 'O', 'P', 'song@gmail.com', '0011223344', 'Japan'),
       ('c000000003', 'Q', 'R', 'family@gmail.com', '5566778899', 'Mexico'),
       ('c000000004', 'S', 'T', 'star@gmail.com', '9876543210', 'Denmark');

INSERT INTO employee (id, first_name, last_name, sex, email, phone_number, role, address, password)
VALUES ('e000000000', 'ADMIN', 'ADMIN', 'FEMALE', 'admin@gmail.com', '0000000000', 'ADMIN', 'admin', 'admin1234'),
       ('e000000001', 'Se', 'Seraphine', 'FEMALE', 'seraphine@gmail.com', '8073827298', 'HR', 'Zaun', 'Se7538618'),
       ('e000000002', 'Py', 'Pyke', 'MALE', 'pyke@gmail.com', '6040489891', 'SALE_MANAGER', 'BilgeWater', 'Py4841842'),
       ('e000000003', 'Vie', 'Viego', 'MALE', 'viego@gmail.com', '1703504060', 'SALE_MANAGER', 'Camavor', 'Vie948796'),
       ('e000000004', 'Aka', 'Akali', 'FEMALE', 'akali@gmail.com', '2170833522', 'DRIVER', 'Ionia', 'Ak1246537'),
       ('e000000005', 'Ca', 'Caitlyn', 'FEMALE', 'caitlyn@gmail.com', '1747557570', 'MANAGER', 'Piltover', 'Ca7744620');


INSERT INTO delivery (id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status,
                      all_product_count)
VALUES ('d000000001', 'c000000003', '2024-10-26', '2024-10-26', 'เครื่องใช้ไฟฟ้า', 'Hua Hin', 'TODO', 5),
       ('d000000002', 'c000000002', null, '2024-10-26', 'เครื่องจักร', 'สงขลา', 'TODO', 10),
       ('d000000003', 'c000000001', '2024-10-28', '2024-10-30', 'สินค้าขนาดใหญ่', 'แพร่', 'TODO', 0);

INSERT INTO product (id, notice_id, delivery_id, item_count, item_detail)
VALUES ('p000000001', null, 'd000000001', 3, 'แอร์'),
       ('p000000002', null, 'd000000001', 2, 'เครื่องบิน'),
       ('p000000003', null, 'd000000002', 5, 'เครื่องดูดฝุ่น'),
       ('p000000004', null, 'd000000002', 2, 'เครื่องบิน'),
       ('p000000005', null, 'd000000002', 3, 'รถกระบะ'),
       ('p000000006', null, 'd000000003', 2, 'บ้าน');

INSERT INTO car (car_registration, driver_id, oil_type, finish_used, car_type)
VALUES ('กก-1234 กรุงเทพมหานคร', 'e000000003', 'แก๊สโซฮอล์ 95', null, '6 ล้อ'),
       ('กข-4567 กรุงเทพมหานคร', 'e000000004', 'ดีเซล B20', null, 'รถสไลด์ 10 ล้อ');

INSERT INTO notice (id, delivery_id, driver_id, car_registration, start_work_date, complete_status)
VALUES ('n000000001', 'd000000001', 'e000000003', 'กก-1234 กรุงเทพมหานคร', '2024-10-26', 'INCOMPLETE'),
       ('n000000002', 'd000000001', 'e000000003', 'กก-1234 กรุงเทพมหานคร', '2024-10-26', 'INCOMPLETE'),
       ('n000000003', 'd000000001', 'e000000003', 'กก-1234 กรุงเทพมหานคร', '2024-10-26', 'INCOMPLETE'),
       ('n000000004', 'd000000003', 'e000000003', 'กก-1234 กรุงเทพมหานคร', '2024-10-26', 'INCOMPLETE');

UPDATE product
SET notice_id = 'n000000004'
WHERE id = 'p000000006';

INSERT INTO counter (table_name, counter)
VALUES ('customer', 4),
       ('employee', 5),
       ('delivery', 3),
       ('product', 6),
       ('car', 2),
       ('notice', 4),
       ('bill', 0);
