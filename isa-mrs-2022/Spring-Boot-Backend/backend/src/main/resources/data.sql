set datestyle = dmy;

--INSERT INTO USERS (id,username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (nextval('cust_seq_user'),'user1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', true, '2017-10-01 21:58:58.508-07');
--INSERT INTO USERS (id,username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (nextval('cust_seq_user'),'user2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com', true, '2017-10-01 18:57:58.508-07');

INSERT INTO administrator (id,username, password, first_name, last_name, email, enabled, last_password_reset_date,name_admin) VALUES (nextval('cust_seq_user'),'admin1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', true, '2017-10-01 21:58:58.508-07','Ogi admin');
INSERT INTO administrator (id,username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (nextval('cust_seq_user'),'admin2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com', true, '2017-10-01 18:57:58.508-07');

INSERT INTO my_user (id,username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (nextval('cust_seq_user'),'myus1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', true, '2017-10-01 21:58:58.508-07');
INSERT INTO my_user (id,username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (nextval('cust_seq_user'),'myus2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'user2@example.com', true, '2017-10-01 18:57:58.508-07');


INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 1); -- user-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 1);
--INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- admin-u dodeljujemo rolu USER
--INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 2); -- admin-u dodeljujemo rolu USER
--INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 2); -- user-u dodeljujemo rolu ADMIN


--BOAT OWNER
INSERT INTO boat_owner (name,email,delete_own_boat) VALUES ('Zarko' , 'vaskebonf22@gmail.com',false);
INSERT INTO boat_owner (name,email,delete_own_boat) VALUES ('Darko' , 'vaskebonf22@gmail.com',false);
INSERT INTO boat_owner (name,email,delete_own_boat) VALUES ('Slavko' , 'vaskebonf22@gmail.com',false);

--COTTAGE OWNER
INSERT INTO cottage_owner (name,email,delete_own_ctg) VALUES ('Peca' , 'vaskebonf22@gmail.com',false);
INSERT INTO cottage_owner (name,email,delete_own_ctg) VALUES ('Zeka' , 'vaskebonf22@gmail.com',false);
INSERT INTO cottage_owner (name,email,delete_own_ctg) VALUES ('Peka' , 'vaskebonf22@gmail.com',false);


--INSTRUCTOR
INSERT INTO instructor (name,email,description,address,delete_ins) VALUES ('Instructor 1' , 'vaskebonf22@gmail.com','Rodjen sam tad i tad..','adr 1',false);
INSERT INTO instructor (name,email,description,address,delete_ins) VALUES ('Instructor 2' ,'vaskebonf22@gmail.com', 'Rodjen sam tad i tad..','adr 2',false);
INSERT INTO instructor (name,email,description,address,delete_ins) VALUES ('Instructor 3' , 'vaskebonf22@gmail.com','Rodjen sam tad i tad..','adr 3',false);

--INSTRUCTOR MARK
INSERT INTO instructor_mark (mark) VALUES (2);
INSERT INTO instructor_mark (mark) VALUES (3);
INSERT INTO instructor_mark (mark) VALUES (5);

--COTTAGE
INSERT INTO cottage (name,address,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Brea Cottage','Wilson 4',23.3556,23.7777 , 'Description 2332',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,address,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Swiiss','Street 21' ,23.3556,23.7777, 'Description 212',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,address,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Lea Araptment','Street Lea',23.3556,23.7777 , 'Description 2grfgf',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,address,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Brea 2','Wilson 51',23.3556,23.7777 , 'Description 2wqee',4,6,'rule behaviour','more informations',false);
INSERT INTO cottage (name,address,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Brea ','Wilson 4' ,23.3556,23.7777, 'Description 2332',3,4,'rule behaviour','more informations',false);
INSERT INTO cottage (name,address,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Swiiss 22','Street 21',23.3556,23.7777 , 'Description 212',5,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,address,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Lea Dep','Street Lea' ,23.3556,23.7777, 'Description 2grfgf',5,2,'rule behaviour','more informations',false);
INSERT INTO cottage (name,address,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Brea Corp','Wilson 51' ,23.3556,23.7777, 'Description 2wqee',2,2,'rule behaviour','more informations',false);
INSERT INTO cottage (name,address,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('A','Wilson 4',23.3556,23.7777 , 'Description 2332',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,address,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('B','Street 21' ,23.3556,23.7777, 'Description 212',4,1,'rule behaviour','more informations',false);
INSERT INTO cottage (name,address,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('C','Street Lea',23.3556,23.7777 , 'Description 2grfgf',4,7,'rule behaviour','more informations',false);
INSERT INTO cottage (name,address,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('D','Wilson 51',23.3556,23.7777 , 'Description 2wqee',4,3,'rule behaviour','more informations',false);



--BOAT
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,address,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 1','type1',9,2,200,230,'adr1',23.3333,54.2222,'capacity 1','description1','navitagtion1','rule_behaviour1','fishing_eqp1','more_info1',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,address,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 2','type2',5,3,300,330,'adr2',23.3333,54.2222,'capacity 2','description2','navitagtion2','rule_behaviour2','fishing_eqp2','more_info2',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,address,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 3','type1',9,3,200,130,'adr3',23.3333,54.2222,'capacity 3','description3','navitagtion3','rule_behaviour3','fishing_eqp3','more_info3',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,address,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 4','type4',7,6,100,440,'adr4',23.3333,54.2222,'capacity 4','description4','navitagtion4','rule_behaviour4','fishing_eqp4','more_info4',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,address,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 5','type5',9,4,500,40,'adr5',23.3333,54.2222,'capacity 5','description5','navitagtion5','rule_behaviour5','fishing_eqp5','more_info5',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,address,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 6','type6',3,4,500,450,'adr6',23.3333,54.2222,'capacity 6','description6','navitagtion6','rule_behaviour6','fishing_eqp6','more_info6',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,address,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 7','type7',4,7,210,200,'adr7',23.3333,54.2222,'capacity 7','description7','navitagtion7','rule_behaviour7','fishing_eqp7','more_info7',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,address,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 8','type8',15,6,220,230,'adr8',23.3333,54.2222,'capacity 8','description8','navitagtion8','rule_behaviour8','fishing_eqp8','more_info8',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,address,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 9','type1',15,3,330,230,'adr9',23.3333,54.2222,'capacity 9','description9','navitagtion9','rule_behaviour9','fishing_eqp9','more_info9',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,address,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 10','type10',25,1,300,130,'adr10',23.3333,54.2222,'capacity 10','description10','navitagtion10','rule_behaviour10','fishing_eqp10','more_info10',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,address,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 11','type11',6,2,200,230,'adr11',23.3333,54.2222,'capacity 11','description11','navitagtion11','rule_behaviour11','fishing_eqp11','more_info11',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,address,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 12','type12',11,2,100,530,'adr12',23.3333,54.2222,'capacity 12','description12','navitagtion12','rule_behaviour12','fishing_eqp12','more_info12',false);


--ADVENTURE
INSERT INTO adventure (name,address,longitude,latitude,description,instructor_id , max_num , rule_behaviour,more_information,cancellation_conditions) VALUES ('put u dolinu' , 'Novi Sad' , 23.3344 ,12.3232,'opsi neki',1,15,'pravila ponasanja','more information','uslovi otkaza');
INSERT INTO adventure (name,address,longitude,latitude,description,instructor_id , max_num , rule_behaviour,more_information,cancellation_conditions) VALUES ('planinski pogled' , 'Nis' , 55.444 ,23.2277,'opsi neki',1,10,'pravila ponasanja','more information','uslovi otkaza');
INSERT INTO adventure (name,address,longitude,latitude,description,instructor_id , max_num , rule_behaviour,more_information,cancellation_conditions) VALUES ('dan na reci' , 'Beograd' , 88.554 ,84.4521,'opsi neki',1,12,'pravila ponasanja','more information','uslovi otkaza');
INSERT INTO adventure (name,address,longitude,latitude,description,instructor_id , max_num , rule_behaviour,more_information,cancellation_conditions) VALUES ('dan na reci 2' , 'Novi Sad' , 88.554 ,84.4521,'opsi neki',1,12,'pravila ponasanja','more information','uslovi otkaza');
INSERT INTO adventure (name,address,longitude,latitude,description,instructor_id , max_num , rule_behaviour,more_information,cancellation_conditions) VALUES ('recni adn' , 'Beograd' , 88.554 ,84.4521,'opsi neki',1,8,'pravila ponasanja','more information','uslovi otkaza');
INSERT INTO adventure (name,address,longitude,latitude,description,instructor_id , max_num , rule_behaviour,more_information,cancellation_conditions) VALUES ('dan na reci 4' , 'Beograd' , 88.554 ,84.4521,'opsi neki',1,15,'pravila ponasanja','more information','uslovi otkaza');

--ADVENTURE ACTION
INSERT INTO adventure_action ( action_start, action_end, max_people_num, place, price, more_information ) VALUES ('15-04-2022' , '18-04-2022', '5' , 'Novi Sad' , 4500 , 'some more information');
INSERT INTO adventure_action ( action_start, action_end, max_people_num, place, price, more_information ) VALUES ('23-04-2022' , '25-04-2022', '1' , 'Novi Sad' , 1500 , 'some more information .');
INSERT INTO adventure_action ( action_start, action_end, max_people_num, place, price, more_information ) VALUES ('23-04-2022' , '25-04-2022', '5' , 'Beograd' , 5500 , 'some more information ...');
INSERT INTO adventure_action ( action_start, action_end, max_people_num, place, price, more_information ) VALUES ('15-07-2022' , '18-07-2022', '2' , 'Nis' , 2000 , 'some more information ......');

--ADVENTURE IMAGE
INSERT INTO adventure_image ( name ) VALUES ('ads10.png');
INSERT INTO adventure_image ( name ) VALUES ('adve.1.png');
INSERT INTO adventure_image ( name ) VALUES ('adve.2.png');
INSERT INTO adventure_image ( name ) VALUES ('adve.3.png');
INSERT INTO adventure_image ( name ) VALUES ('adve.4.png');
INSERT INTO adventure_image ( name ) VALUES ('adve.5.png');

--ADVENTURE ADVENTURE IMAGE
INSERT INTO adventure_adventure_images(adventure_id , adventure_images_id) VALUES (1,1);
INSERT INTO adventure_adventure_images(adventure_id , adventure_images_id) VALUES (1,2);
INSERT INTO adventure_adventure_images(adventure_id , adventure_images_id) VALUES (1,3);
INSERT INTO adventure_adventure_images(adventure_id , adventure_images_id) VALUES (1,4);
INSERT INTO adventure_adventure_images(adventure_id , adventure_images_id) VALUES (2,1);
INSERT INTO adventure_adventure_images(adventure_id , adventure_images_id) VALUES (2,5);
INSERT INTO adventure_adventure_images(adventure_id , adventure_images_id) VALUES (2,6);
INSERT INTO adventure_adventure_images(adventure_id , adventure_images_id) VALUES (3,1);
INSERT INTO adventure_adventure_images(adventure_id , adventure_images_id) VALUES (4,1);
INSERT INTO adventure_adventure_images(adventure_id , adventure_images_id) VALUES (5,1);
INSERT INTO adventure_adventure_images(adventure_id , adventure_images_id) VALUES (6,1);

--ADVENTURE MARK
INSERT INTO adventure_mark (mark) VALUES (2);
INSERT INTO adventure_mark (mark) VALUES (3);
INSERT INTO adventure_mark (mark) VALUES (2);
INSERT INTO adventure_mark (mark) VALUES (5);

--ADVENTURE PRICELIST
INSERT INTO adventure_pricelist (price,description) VALUES (5000,'3 osobe');
INSERT INTO adventure_pricelist (price,description) VALUES (4000,'2 osobe');
INSERT INTO adventure_pricelist (price,description) VALUES (2500,'1 osoba');
INSERT INTO adventure_pricelist (price,description) VALUES (7000,'max 7 osoba');


--ADVENTURE RESERVATION
INSERT INTO adventure_reservation (reservation_start,reservation_end,active,adventure_pricelist_id) VALUES ('15-04-2022','18-04-2022',true,1);
INSERT INTO adventure_reservation (reservation_start,reservation_end,active,adventure_pricelist_id) VALUES ('10-05-2022','15-05-2022',true,1);
INSERT INTO adventure_reservation (reservation_start,reservation_end,active,adventure_pricelist_id) VALUES ('23-04-2022','25-04-2022',true,2);
INSERT INTO adventure_reservation (reservation_start,reservation_end,active,adventure_pricelist_id) VALUES ('15-07-2022','18-07-2022',true,4);

--ADVENTURE COMPLAINT
INSERT INTO adventure_complaint(description,my_user_id,adventure_id) VALUES ('opsi zalbe' , 4,1);

--INSTRUCTOR COMPLAINT
INSERT INTO instructor_complaint(description,my_user_id,instructor_id) VALUES ('opsi zalbe' , 4,1);


-------------------------------------------------BOAT

--BOAT ACTION
INSERT INTO boat_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('11-05-2022' , '13-05-2022', '3'  , 4500 , 'some more information');
INSERT INTO boat_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('21-06-2022' , '28-06-2022', '1'  , 5500 , 'some more information .');
INSERT INTO boat_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('19-06-2022' , '21-06-2022', '3'  , 5500 , 'some more information ...');
INSERT INTO boat_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('15-07-2022' , '18-07-2022', '2'  , 4000 , 'some more information ......');


INSERT INTO boat_image ( name ) VALUES ('ads9.png');
INSERT INTO boat_image ( name ) VALUES ('ads8.jpg');
INSERT INTO boat_image ( name ) VALUES ('ads6.jpg');

--BOAT MARK
INSERT INTO boat_mark (mark) VALUES (1);
INSERT INTO boat_mark (mark) VALUES (3);
INSERT INTO boat_mark (mark) VALUES (1);
INSERT INTO boat_mark (mark) VALUES (5);


--BOAT PRICELIST
INSERT INTO boat_pricelist (price,description) VALUES (5000,'max 3 osobe');
INSERT INTO boat_pricelist (price,description) VALUES (4000,'2 osobe');
INSERT INTO boat_pricelist (price,description) VALUES (6500,'4 osoba');
INSERT INTO boat_pricelist (price,description) VALUES (14000,'9 osoba');

--BOAT RESERVATION
INSERT INTO boat_reservation (reservation_start,reservation_end,active,boat_pricelist_id) VALUES ('11-05-2022','13-05-2022',true,1);
INSERT INTO boat_reservation (reservation_start,reservation_end,active,boat_pricelist_id) VALUES ('21-06-2022','28-06-2022',true,3);
INSERT INTO boat_reservation (reservation_start,reservation_end,active,boat_pricelist_id) VALUES ('21-06-2022','21-06-2022',true,4);
INSERT INTO boat_reservation (reservation_start,reservation_end,active,boat_pricelist_id) VALUES ('15-07-2022','18-07-2022',true,2);



--BOAT BOAT IMAGE
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (1,1);
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (1,2);
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (1,3);
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (2,1);
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (3,1);
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (4,1);
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (5,1);
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (6,1);
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (7,1);
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (8,1);
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (9,1);
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (10,1);
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (11,1);
INSERT INTO boat_boat_images(boat_id , boat_images_id) VALUES (12,1);

--BOAT COMPLAINT
INSERT INTO boat_complaint(description,my_user_id,boat_id) VALUES ('opsi zalbe' , 3,2);

--BOAT OWNER COMPLAINT
INSERT INTO boat_owner_complaint(description,my_user_id,boat_owner_id) VALUES ('opsi zalbe' , 3,1);

-------------------------------------------------COTTAGE

--COTTAGE ACTION
INSERT INTO cottage_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('01-05-2022' , '03-05-2022', '5'  , 4500 , 'some more information');
INSERT INTO cottage_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('11-06-2022' , '18-06-2022', '1'  , 5500 , 'some more information .');
INSERT INTO cottage_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('09-06-2022' , '22-06-2022', '5'  , 15500 , 'some more information ...');
INSERT INTO cottage_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('04-07-2022' , '08-07-2022', '2'  , 4000 , 'some more information ......');

--COTTAGE IMAGE
INSERT INTO cottage_image ( name ) VALUES ( 'ads6.jpg');
INSERT INTO cottage_image ( name ) VALUES ( 'ads2.jpg');
INSERT INTO cottage_image ( name ) VALUES ( 'ads4.jpg');
INSERT INTO cottage_image ( name ) VALUES ( 'ads7.jpg');
--COTTAGE COTTAGE IMAGE
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (1,1);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (1,2);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (1,3);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (1,4);
--INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (1,3);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (2,3);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (2,1);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (3,3);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (3,2);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (4,3);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (5,2);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (6,2);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (6,3);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (6,1);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (7,1);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (8,1);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (9,3);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (10,2);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (11,1);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (12,1);

--COTTAGE MARK
INSERT INTO cottage_mark (mark) VALUES (4);
INSERT INTO cottage_mark (mark) VALUES (4);
INSERT INTO cottage_mark (mark) VALUES (2);
INSERT INTO cottage_mark (mark) VALUES (5);
INSERT INTO cottage_mark (mark) VALUES (2);
INSERT INTO cottage_mark (mark) VALUES (4);
INSERT INTO cottage_mark (mark) VALUES (3);
INSERT INTO cottage_mark (mark) VALUES (5);


--COTTAGE PRICELIST
INSERT INTO cottage_pricelist (price,description) VALUES (3000,'max 2 osobe');
INSERT INTO cottage_pricelist (price,description) VALUES (5000,'3 osobe');
INSERT INTO cottage_pricelist (price,description) VALUES (6500,'6 osoba');
INSERT INTO cottage_pricelist (price,description) VALUES (14000,'9 osoba');

--COTTAGE RESERVATION
INSERT INTO cottage_reservation (reservation_start,reservation_end,active,cottage_pricelist_id) VALUES ('01-05-2022','03-05-2022',true,4);
INSERT INTO cottage_reservation (reservation_start,reservation_end,active,cottage_pricelist_id) VALUES ('11-06-2022','18-06-2022',true,3);
INSERT INTO cottage_reservation (reservation_start,reservation_end,active,cottage_pricelist_id) VALUES ('11-06-2022','18-06-2022',true,3);
INSERT INTO cottage_reservation (reservation_start,reservation_end,active,cottage_pricelist_id) VALUES ('04-07-2022','08-07-2022',true,4);



--ADVENTURE ADVENTURE ACTION
INSERT INTO adventure_adventure_actions(adventure_id,adventure_actions_id) VALUES (1,1);

--ADVENTURE ADVENTURE PRICELIST
INSERT INTO adventure_adventure_pricelists(adventure_id,adventure_pricelists_id) VALUES (1,1);

--ADVENTURE ADVENTURE RESERVATION
INSERT INTO adventure_adventure_reservations(adventure_id,adventure_reservations_id) VALUES (1,1);

--ADVENTURE MARKS
INSERT INTO adventure_marks(adventure_id,marks_id) VALUES (1,1);


--BOAT BOAT ACTION
INSERT INTO boat_boat_actions(boat_id,boat_actions_id) VALUES (1,1);

--BOAT BOAT PRICELIST
INSERT INTO boat_boat_pricelists(boat_id,boat_pricelists_id) VALUES (1,2);

--BOAT BOAT RESERVATION
INSERT INTO boat_boat_resevations(boat_id,boat_resevations_id) VALUES (1,2);

--BOAT MARKS
INSERT INTO boat_marks(boat_id,marks_id) VALUES (1,1);


--COTTAGE COTTAGE ACTION
INSERT INTO cottage_cottage_actions(cottage_id,cottage_actions_id) VALUES (1,1);

--COTTAGE COTTAGE PRICELIST
INSERT INTO cottage_cottage_pricelists(cottage_id,cottage_pricelists_id) VALUES (1,2);

--COTTAGE COTTAGE RESERVATION
INSERT INTO cottage_cottage_resevations(cottage_id,cottage_resevations_id) VALUES (1,2);

--COTTAGE MARKS
INSERT INTO cottage_marks(cottage_id,marks_id) VALUES (1,1);
INSERT INTO cottage_marks(cottage_id,marks_id) VALUES (3,2);
INSERT INTO cottage_marks(cottage_id,marks_id) VALUES (3,3);
INSERT INTO cottage_marks(cottage_id,marks_id) VALUES (11,4);
INSERT INTO cottage_marks(cottage_id,marks_id) VALUES (11,5);
INSERT INTO cottage_marks(cottage_id,marks_id) VALUES (11,6);

--INSTRUCTOR MARKS
INSERT INTO instructor_marks(instructor_id,marks_id) VALUES (1,1);


--MY USER ADVENTURES
INSERT INTO adventure_my_users(adventure_id,my_users_id) VALUES (1,4);

--MY USER BOATS
INSERT INTO boat_my_users(boat_id,my_users_id) VALUES (2,3);

--MY USER COTTAGES
INSERT INTO cottage_my_users(cottage_id,my_users_id) VALUES (2,4);