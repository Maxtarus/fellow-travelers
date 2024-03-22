INSERT INTO roles(type) VALUES ('ADMIN');
INSERT INTO roles(type) VALUES ('PASSENGER');
INSERT INTO roles(type) VALUES ('DRIVER');

--Пароли 111, 222, 333, 444 и 555 соответственно
INSERT INTO users(email, password, first_name, last_name, phone_number, birth_date) VALUES ('admin@mail.ru', '$2a$10$/4PupSZXYVF4U6mD8CYY/emxqy3ZYodks.oGZfT062YJL1c1G0qZG', 'Николай', 'Касперский', '89997775544', '1998-08-21');
INSERT INTO users(email, password, first_name, last_name, phone_number, birth_date) VALUES ('starmax@yandex.ru', '$2a$10$7fiCiyEmngc5NaV0LlAneObnrnQIwNgt3o/TecBBO1z8.CZcKcAmi', 'Максим', 'Старостин', '89106154366', '2002-10-11');
INSERT INTO users(email, password, first_name, last_name, phone_number, birth_date) VALUES ('imosolov@gmail.com', '$2a$10$FVX8BNQwqI2eAEjPgPAx9uz/71gfz9WwlaC.P/toFnpds4AGcEP.K', 'Иван', 'Мосолов', '89106176666', '2002-09-02');
INSERT INTO users(email, password, first_name, last_name, phone_number, birth_date) VALUES ('www@gmail.com', '$2a$10$LccnZdNs2lUcU.Ytu8Ldh.icRBTKfh51GJEmMu6DrDXhFRdJ/kYcC', 'Владислав', 'Даванков', '89886541832', '1988-04-22');
INSERT INTO users(email, password, first_name, last_name, phone_number, birth_date) VALUES ('user@yandex.ru', '$2a$10$SZkhnQ/4CbujR3hzvXyuqe8bdlZIL4uSXPDYE37KseljHm5B1wLte', 'Николай', 'Харитонов', '89886541832', '1956-05-16');

INSERT INTO user_roles(role_id, user_id) VALUES (1, 1);
INSERT INTO user_roles(role_id, user_id) VALUES (1, 2);
INSERT INTO user_roles(role_id, user_id) VALUES (2, 2);
INSERT INTO user_roles(role_id, user_id) VALUES (2, 3);
INSERT INTO user_roles(role_id, user_id) VALUES (2, 4);
INSERT INTO user_roles(role_id, user_id) VALUES (3, 2);
INSERT INTO user_roles(role_id, user_id) VALUES (3, 3);
INSERT INTO user_roles(role_id, user_id) VALUES (3, 5);

INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Казань', 'Санкт-Петербург', '2024-03-25 07:30:00', '2024-03-26 09:50:00', 4, 3000, 'NOT_COMPLETED', 2);
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Москва', 'Рязань', '2024-03-27 07:00:00', '2024-03-27 09:50:00', 1, 500, 'NOT_COMPLETED', 3);
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Рязань', 'Москва', '2024-03-27 19:00:00', '2024-03-27 21:20:00', 2, 700, 'NOT_COMPLETED', 3);
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Тамбов', 'Мичуринск', '2024-03-29 19:50:00', '2024-03-30 12:20:00', 2, 700, 'COMPLETED', 5);

INSERT INTO requests(status, passenger_id, trip_id) VALUES ('UNDER_CONSIDERATION', 3, 1);
INSERT INTO requests(status, passenger_id, trip_id) VALUES ('UNDER_CONSIDERATION', 4, 1);
INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 4, 2);
INSERT INTO requests(status, passenger_id, trip_id) VALUES ('REJECTED', 4, 4);
