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

INSERT INTO car (car_registration, driver_id, oil_type, finish_used, car_type)
VALUES ('ka-1234', 'e000000001', 'Bensin', '2024-10-24', 'Benz'),
       ('ka-1235', 'e000000002', 'Bensin', '2024-10-24', 'Benz');

INSERT INTO delivery(id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status,
                     all_product_count)
VALUES ('d000000001', 'c000000003', '2024-10-26', '2024-10-26', 'เครื่องใช้ไฟฟ้า', 'Hua Hin', 'TODO', 5);

INSERT INTO product (id, notice_id, delivery_id, item_count, item_detail)
VALUES ('p000000001', null, 'd000000001', 3, 'แอร์'),
       ('p000000002', null, 'd000000001', 2, 'เครื่องบิน');

INSERT INTO notice (id, delivery_id, driver_id, car_registration, start_work_date, complete_status)
VALUES ('n000000001', 'd000000001', 'e000000001', 'ka-1234', '2024-10-26', 'INCOMPLETE'),
       ('n000000002', 'd000000001', 'e000000001', 'ka-1234', '2024-10-26', 'INCOMPLETE'),
       ('n000000003', 'd000000001', 'e000000001', 'ka-1234', '2024-10-26', 'INCOMPLETE');

-- Insert customer data
-- INSERT INTO customer (id, first_name, last_name, email, phone_number, address)
-- VALUES ('c000000001', 'M', 'N', 'alphabet@gmail.com', '0123456789', 'Paris'),
--        ('c000000002', 'O', 'P', 'song@gmail.com', '0011223344', 'Japan'),
--        ('c000000003', 'Q', 'R', 'family@gmail.com', '5566778899', 'Mexico'),
--        ('c000000004', 'S', 'T', 'star@gmail.com', '9876543210', 'Denmark');
--
-- -- Insert employee data
-- INSERT INTO employee (id, first_name, last_name, sex, email, phone_number, role, address, password)
-- VALUES ('e000000001', 'Se', 'Seraphine', 'FEMALE', 'seraphine@gmail.com', '8073827298', 'Support', 'Zaun', 'Se7538618'),
--        ('e000000002', 'Py', 'Pyke', 'MALE', 'pyke@gmail.com', '8073827299', 'Support', 'BilgeWater', 'Py7538618');
--
-- -- Insert car data (ensures car_registration foreign key constraint in notice is met)
-- INSERT INTO car (car_registration, driver_id, oil_type, finish_used, car_type)
-- VALUES ('ka-1234', 'e000000001', 'Petrol', '2024-10-25', 'Sedan'),
--        ('ka-1235', 'e000000002', 'Petrol', '2024-10-25', 'Sedan');
--
-- -- Insert delivery data
-- INSERT INTO delivery (id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status)
-- VALUES ('d000000001', 'c000000003', '2024-10-26', '2024-10-26', 'เครื่องใช้ไฟฟ้า', 'Hua Hin', 'TODO');
--
-- -- Insert notice data after car and delivery records are inserted
-- INSERT INTO notice (id, delivery_id, driver_id, car_registration, start_work_date, complete_status)
-- VALUES ('n000000001', 'd000000001', 'e000000001', 'ka-1234', '2024-10-26', 'INCOMPLETE');
--
-- INSERT INTO notice (id, delivery_id, driver_id, car_registration, start_work_date, complete_status)
-- VALUES ('n000000002', 'd000000001', 'e000000001', 'ka-1234', '2024-10-26', 'INCOMPLETE');
--
-- INSERT INTO notice (id, delivery_id, driver_id, car_registration, start_work_date, complete_status)
-- VALUES ('n000000003', 'd000000001', 'e000000001', 'ka-1234', '2024-10-26', 'INCOMPLETE');
--
-- INSERT INTO notice (id, delivery_id, driver_id, car_registration, start_work_date, complete_status)
-- VALUES ('n000000004', 'd000000001', 'e000000002', 'ka-1235', '2024-10-26', 'INCOMPLETE');
