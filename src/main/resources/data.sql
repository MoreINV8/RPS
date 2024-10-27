INSERT INTO customer (id, first_name, last_name, email, phone_number, address)
VALUES ('c000000001', 'M', 'N', 'alphabet@gmail.com', '0123456789', 'Paris'),
       ('c000000002', 'O', 'P', 'song@gmail.com', '0011223344', 'Japan'),
       ('c000000003', 'Q', 'R', 'family@gmail.com', '5566778899', 'Mexico'),
       ('c000000004', 'S', 'T', 'star@gmail.com', '9876543210', 'Denmark');

INSERT INTO employee(id, first_name, last_name, sex, email, phone_number, role, address, password)
VALUES ('e000000001', 'Se', 'Seraphine', 'FEMALE', 'seraphine@gmail.com', '8073827298', 'Support', 'Zaun', 'Se7538618'),
       ('e000000002', 'Py', 'Pyke', 'MALE', 'pyke@gmail.com', '6040489891', 'Support', 'BilgeWater', 'Py4841842'),
       ('e000000003', 'Vie', 'Viego', 'MALE', 'viego@gmail.com', '1703504060', 'Jungle', 'Camavor', 'Vie948796'),
       ('e000000004', 'Aka', 'Akali', 'FEMALE', 'akali@gmail.com', '2170833522', 'Mid', 'Ionia', 'Ak1246537'),
       ('e000000005', 'Ca', 'Caitlyn', 'FEMALE', 'caitlyn@gmail.com', '1747557570', 'ADC', 'Piltover', 'Ca7744620');

INSERT INTO delivery(id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status,
                     all_product_count)
VALUES ('d000000001', 'c000000003', '2024-10-26', '2024-10-26', 'เครื่องใช้ไฟฟ้า', 'Hua Hin', 'TODO', 5);

INSERT INTO product (id, notice_id, delivery_id, item_count, item_detail)
VALUES ('p000000001', null, 'd000000001', 3, 'แอร์'),
       ('p000000002', null, 'd000000001', 2, 'เครื่องบิน');

INSERT INTO car (car_registration, driver_id, oil_type, finish_used, car_type)
VALUES ('กก-1234 กรุงเทพมหานคร', 'e000000003', 'แก๊สโซฮอล์ 95', null, '6 ล้อ'),
       ('กข-4567 กรุงเทพมหานคร', 'e000000004', 'ดีเซล B20', null, 'รถสไลด์ 10 ล้อ')