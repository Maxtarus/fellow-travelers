INSERT INTO roles(type) VALUES ('ADMIN');
INSERT INTO roles(type) VALUES ('PASSENGER');
INSERT INTO roles(type) VALUES ('DRIVER');

--Пароль у всех пользователей одинаковый - "1"
INSERT INTO users(email, password, first_name, last_name, phone_number, birth_date) VALUES ('admin@mail.ru', '$2a$10$/4PupSZXYVF4U6mD8CYY/emxqy3ZYodks.oGZfT062YJL1c1G0qZG', 'Роман', 'Касперский', '89997775544', '1998-08-21');
INSERT INTO users(email, password, first_name, last_name, phone_number, birth_date) VALUES ('starmax@yandex.ru', '$2a$10$/4PupSZXYVF4U6mD8CYY/emxqy3ZYodks.oGZfT062YJL1c1G0qZG', 'Максим', 'Старостин', '89106154366', '2002-10-11');
INSERT INTO users(email, password, first_name, last_name, phone_number, birth_date) VALUES ('imosolov@gmail.com', '$2a$10$/4PupSZXYVF4U6mD8CYY/emxqy3ZYodks.oGZfT062YJL1c1G0qZG', 'Иван', 'Мосолов', '89106176666', '2002-09-02');
INSERT INTO users(email, password, first_name, last_name, phone_number, birth_date) VALUES ('davankov@gmail.com', '$2a$10$/4PupSZXYVF4U6mD8CYY/emxqy3ZYodks.oGZfT062YJL1c1G0qZG', 'Владислав', 'Даванков', '89886541832', '1988-04-22');
INSERT INTO users(email, password, first_name, last_name, phone_number, birth_date) VALUES ('haritonov_n@yandex.ru', '$2a$10$/4PupSZXYVF4U6mD8CYY/emxqy3ZYodks.oGZfT062YJL1c1G0qZG', 'Николай', 'Харитонов', '89886541832', '1956-05-16');

--Пользователь с id=1 - администратор
INSERT INTO user_roles(role_id, user_id) VALUES (1, 1);
--Все пользователи - пассажиры
INSERT INTO user_roles(role_id, user_id) VALUES (2, 1);
INSERT INTO user_roles(role_id, user_id) VALUES (2, 2);
INSERT INTO user_roles(role_id, user_id) VALUES (2, 3);
INSERT INTO user_roles(role_id, user_id) VALUES (2, 4);
INSERT INTO user_roles(role_id, user_id) VALUES (2, 5);
--Пользователи с id=2, id=3, id=5 - водители
INSERT INTO user_roles(role_id, user_id) VALUES (3, 2);
INSERT INTO user_roles(role_id, user_id) VALUES (3, 3);
INSERT INTO user_roles(role_id, user_id) VALUES (3, 5);

--Поездки водителя с id=2
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Казань', 'Санкт-Петербург', '2024-03-25 07:30:00', '2024-03-26 09:50:00', 4, 3000, 'NOT_COMPLETED', 2);
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Санкт-Петербург', 'Петрозаводск', '2024-03-28 07:00:00', '2024-03-29 10:35:00', 1, 550, 'NOT_COMPLETED', 2);
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Санкт-Петербург', 'Хельсинки', '2024-04-02 05:30:00', '2024-04-04 13:40:00', 2, 3000, 'NOT_COMPLETED', 2);
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Сочи', 'Краснодар', '2024-05-05 07:00:00', '2024-05-05 15:40:00', 0, 330, 'COMPLETED', 2);
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Джубга', 'Феодосия', '2024-05-10 07:45:00', '2024-05-10 18:25:00', 0, 470, 'COMPLETED', 2);

--Поездки водителя с id=3
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Москва', 'Рязань', '2024-03-27 07:00:00', '2024-03-27 09:50:00', 2, 500, 'NOT_COMPLETED', 3);
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Тула', 'Рязань', '2024-04-10 17:30:00', '2024-04-10 23:00:00', 1, 1100, 'NOT_COMPLETED', 3);
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Рязань', 'Москва', '2024-03-27 19:00:00', '2024-03-27 21:20:00', 0, 700, 'COMPLETED', 3);
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Тула', 'Рязань', '2024-04-10 17:30:00', '2024-04-10 23:00:00', 0, 1100, 'COMPLETED', 3);

--Поездки водителя с id=5
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Тамбов', 'Мичуринск', '2024-03-29 19:50:00', '2024-03-30 12:20:00', 2, 700, 'NOT_COMPLETED', 5);
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Мичуринск', 'Ряжск', '2024-04-15 12:30:00', '2024-04-15 17:30:00', 1, 990, 'NOT_COMPLETED', 5);
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Скопин', 'Пронск', '2024-04-10 17:30:00', '2024-04-10 23:00:00', 0, 840, 'COMPLETED', 5);
INSERT INTO trips(start_point, final_point, departure_time, arrival_time, free_seats, price, status, driver_id) VALUES ('Скопин', 'Пронск', '2024-05-10 07:45:00', '2024-05-10 18:25:00', 3, 400, 'COMPLETED', 5);

INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 1, 4);
INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 4, 4);
INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 5, 4);

INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 3, 5);
INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 5, 5);

INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 1, 8);
INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 2, 8);
INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 4, 8);
INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 5, 8);

INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 5, 9);

INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 1, 12);
INSERT INTO requests(status, passenger_id, trip_id) VALUES ('REJECTED', 3, 12);
INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 2, 13);
INSERT INTO requests(status, passenger_id, trip_id) VALUES ('APPROVED', 4, 13);

INSERT INTO marks(to_user_id, from_user_id, trip_id, mark_type) VALUES (2, 1, 4, 'EXCELLENT');
INSERT INTO marks(to_user_id, from_user_id, trip_id, mark_type) VALUES (2, 4, 4, 'GOOD');
INSERT INTO marks(to_user_id, from_user_id, trip_id, mark_type) VALUES (2, 5, 4, 'NORMAL');

INSERT INTO marks(to_user_id, from_user_id, trip_id, mark_type) VALUES (2, 3, 5, 'EXCELLENT');
INSERT INTO marks(to_user_id, from_user_id, trip_id, mark_type) VALUES (2,  5, 5, 'EXPECTATIONS_NOT_FULFILLED');

INSERT INTO marks(to_user_id, from_user_id, trip_id, mark_type) VALUES (3, 1, 8, 'EXCELLENT');
INSERT INTO marks(to_user_id, from_user_id, trip_id, mark_type) VALUES (3, 2, 8, 'EXCELLENT');
INSERT INTO marks(to_user_id, from_user_id, trip_id, mark_type) VALUES (3, 4, 8, 'GOOD');

INSERT INTO marks(to_user_id, from_user_id, trip_id, mark_type) VALUES (3, 5, 9, 'EXCELLENT');

INSERT INTO marks(to_user_id, from_user_id, trip_id, mark_type) VALUES (5, 1, 12, 'DISLIKE');

INSERT INTO marks(to_user_id, from_user_id, trip_id, mark_type) VALUES (5, 2, 13, 'EXPECTATIONS_NOT_FULFILLED');






