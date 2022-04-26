--INSERT INTO administrator (id,username, password, name, email, enabled, last_password_reset_date)
--VALUES (1,'admin', '$2a$04$.AFp9ZqCNwkKmhF.iRr.Gu7jMwE8/zFWl0ddng2UYGek.y3rQ71tu', 'Marko Markovic', 'user@example.com', true, '2017-10-01 21:58:58.508-07' );
--
--INSERT INTO my_user (id,username, password, name, email, enabled, last_password_reset_date)
--VALUES (1,'user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko Markovic', 'user@example.com', true, '2017-10-01 21:58:58.508-07' );

--INSERT INTO administrator (name_admin,delete_adm)
--VALUES ('admin1',true );



--INSERT INTO USERS (username, password, name, email, enabled, last_password_reset_date) VALUES ('admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola Nikolic', 'admin@example.com', true, '2017-10-01 18:57:58.508-07');

INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');

--INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- user-u dodeljujemo rolu USER
--INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 1); -- admin-u dodeljujemo rolu USER
--INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2); -- user-u dodeljujemo rolu ADMIN