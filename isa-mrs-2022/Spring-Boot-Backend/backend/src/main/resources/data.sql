--INSERT INTO boat (name,adress,description) VALUES ('Swiss House','Street 1' , 'Description 1');
--INSERT INTO boat (name,adress,description) VALUES ('Brea Cottage','Street Brea' , 'Description 2');
--INSERT INTO cottage_image( name , image_byte) VALUES ('ime1' , get_byte('TEkst'::bytea, 4));
SET datestyle = dmy;
--CREATE EXTENSION pgcrypto;

--MY USER
INSERT INTO my_user (name,email,username,password,active,delete_usr) VALUES ('Filip' ,'ff', 'vaskebonf22@gmail.com' , crypt('ff', gen_salt('bf')),true,false);
INSERT INTO my_user (name,email,username,password,active,delete_usr) VALUES ('Marko' ,'mm', 'vaskebonf22@gmail.com' , crypt('mm', gen_salt('bf')),true,false);
INSERT INTO my_user (name,email,username,password,active,delete_usr) VALUES ('Nikola' ,'nn', 'vaskebonf22@gmail.com' , crypt('nn', gen_salt('bf')),true,false);
INSERT INTO my_user (name,email,username,password,active,delete_usr) VALUES ('Petar' ,'pp', 'vaskebonf22@gmail.com' , crypt('pp', gen_salt('bf')),true,false);
INSERT INTO my_user (name,email,username,password,active,delete_usr) VALUES ('Sasa' ,'ss', 'vaskebonf22@gmail.com' , crypt('ss', gen_salt('bf')),true,false);

--BOAT OWNER
INSERT INTO boat_owner (name,email,delete_own_boat) VALUES ('Zarko' , 'vaskebonf22@gmail.com',false);
INSERT INTO boat_owner (name,email,delete_own_boat) VALUES ('Darko' , 'vaskebonf22@gmail.com',false);
INSERT INTO boat_owner (name,email,delete_own_boat) VALUES ('Slavko' , 'vaskebonf22@gmail.com',false);

--COTTAGE OWNER
INSERT INTO cottage_owner (name,email,delete_own_ctg) VALUES ('Peca' , 'vaskebonf22@gmail.com',false);
INSERT INTO cottage_owner (name,email,delete_own_ctg) VALUES ('Zeka' , 'vaskebonf22@gmail.com',false);
INSERT INTO cottage_owner (name,email,delete_own_ctg) VALUES ('Peka' , 'vaskebonf22@gmail.com',false);

--INSTRUCTOR
INSERT INTO instructor (name,email,delete_ins) VALUES ('Instructor 1' , 'vaskebonf22@gmail.com',false);
INSERT INTO instructor (name,email,delete_ins) VALUES ('Instructor 2' ,'vaskebonf22@gmail.com', false);
INSERT INTO instructor (name,email,delete_ins) VALUES ('Instructor 3' , 'vaskebonf22@gmail.com',false);


--ADMINISTRATOR
INSERT INTO administrator( name , password , active , delete_adm ) VALUES ('Admin 1' , crypt('dwdwdw', gen_salt('bf')) , true , false);
INSERT INTO administrator( name , password , active , delete_adm ) VALUES ('Admin 2' , crypt('123', gen_salt('bf')) , true , false);

-------------------------------------------------ADVENTURE

--INSTRUCTOR MARK
INSERT INTO instructor_mark (mark) VALUES (2);
INSERT INTO instructor_mark (mark) VALUES (3);
INSERT INTO instructor_mark (mark) VALUES (5);

--ADVENTURE
INSERT INTO adventure (name,adress,longitude,latitude,description,instructor_id , max_num , rule_behaviour,more_information,cancellation_conditions) VALUES ('put u dolinu' , 'Novi Sad' , 23.3344 ,12.3232,'opsi neki',1,15,'pravila ponasanja','more information','uslovi otkaza');
INSERT INTO adventure (name,adress,longitude,latitude,description,instructor_id , max_num , rule_behaviour,more_information,cancellation_conditions) VALUES ('planinski pogled' , 'Nis' , 55.444 ,23.2277,'opsi neki',1,10,'pravila ponasanja','more information','uslovi otkaza');
INSERT INTO adventure (name,adress,longitude,latitude,description,instructor_id , max_num , rule_behaviour,more_information,cancellation_conditions) VALUES ('dan na reci' , 'Beograd' , 88.554 ,84.4521,'opsi neki',1,12,'pravila ponasanja','more information','uslovi otkaza');


--ADVENTURE ACTION
INSERT INTO adventure_action ( action_start, action_end, max_people_num, place, price, more_information ) VALUES ('15-04-2022' , '18-04-2022', '5' , 'Novi Sad' , 4500 , 'some more information');
INSERT INTO adventure_action ( action_start, action_end, max_people_num, place, price, more_information ) VALUES ('23-04-2022' , '25-04-2022', '1' , 'Novi Sad' , 1500 , 'some more information .');
INSERT INTO adventure_action ( action_start, action_end, max_people_num, place, price, more_information ) VALUES ('23-04-2022' , '25-04-2022', '5' , 'Beograd' , 5500 , 'some more information ...');
INSERT INTO adventure_action ( action_start, action_end, max_people_num, place, price, more_information ) VALUES ('15-07-2022' , '18-07-2022', '2' , 'Nis' , 2000 , 'some more information ......');

--ADVENTURE IMAGE
INSERT INTO adventure_image ( name , image_byte) VALUES ('adventure_1' , lo_import('C:/tmp/adventure1.jpg'));
INSERT INTO adventure_image ( name , image_byte) VALUES ('adventure_2' , lo_import('C:/tmp/adventure2.jpg'));

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
INSERT INTO adventure_complaint(description,my_user_id,adventure_id) VALUES ('opsi zalbe' , 1,1);

--INSTRUCTOR COMPLAINT
INSERT INTO instructor_complaint(description,my_user_id,instructor_id) VALUES ('opsi zalbe' , 1,1);
-------------------------------------------------BOAT

--BOAT ACTION
INSERT INTO boat_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('11-05-2022' , '13-05-2022', '3'  , 4500 , 'some more information');
INSERT INTO boat_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('21-06-2022' , '28-06-2022', '1'  , 5500 , 'some more information .');
INSERT INTO boat_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('19-06-2022' , '21-06-2022', '3'  , 5500 , 'some more information ...');
INSERT INTO boat_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('15-07-2022' , '18-07-2022', '2'  , 4000 , 'some more information ......');

--BOAT IMAGE
INSERT INTO boat_image ( name , image_byte) VALUES ('boat_1' , lo_import('C:/tmp/boat1.jpg'));
INSERT INTO boat_image ( name , image_byte) VALUES ('boat_2' , lo_import('C:/tmp/boat2.jpg'));

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


--BOAT
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,adress,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 1','type1','5','2','200ks','230km','adr1',23.3333,54.2222,'capacity 1','description1','navitagtion1','rule_behaviour1','fishing_eqp1','more_info1',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,adress,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 2','type2','5','2','200ks','230km','adr2',23.3333,54.2222,'capacity 2','description2','navitagtion2','rule_behaviour2','fishing_eqp2','more_info2',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,adress,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 3','type3','5','2','200ks','230km','adr3',23.3333,54.2222,'capacity 3','description3','navitagtion3','rule_behaviour3','fishing_eqp3','more_info3',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,adress,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 4','type4','5','2','200ks','230km','adr4',23.3333,54.2222,'capacity 4','description4','navitagtion4','rule_behaviour4','fishing_eqp4','more_info4',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,adress,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 5','type5','5','2','200ks','230km','adr5',23.3333,54.2222,'capacity 5','description5','navitagtion5','rule_behaviour5','fishing_eqp5','more_info5',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,adress,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 6','type6','5','2','200ks','230km','adr6',23.3333,54.2222,'capacity 6','description6','navitagtion6','rule_behaviour6','fishing_eqp6','more_info6',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,adress,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 7','type7','5','2','200ks','230km','adr7',23.3333,54.2222,'capacity 7','description7','navitagtion7','rule_behaviour7','fishing_eqp7','more_info7',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,adress,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 8','type8','5','2','200ks','230km','adr8',23.3333,54.2222,'capacity 8','description8','navitagtion8','rule_behaviour8','fishing_eqp8','more_info8',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,adress,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 9','type9','5','2','200ks','230km','adr9',23.3333,54.2222,'capacity 9','description9','navitagtion9','rule_behaviour9','fishing_eqp9','more_info9',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,adress,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 10','type10','5','2','200ks','230km','adr10',23.3333,54.2222,'capacity 10','description10','navitagtion10','rule_behaviour10','fishing_eqp10','more_info10',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,adress,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 11','type11','5','2','200ks','230km','adr11',23.3333,54.2222,'capacity 11','description11','navitagtion11','rule_behaviour11','fishing_eqp11','more_info11',false);
INSERT INTO boat (name,type_boat,lenght,engine_num,engine_power,max_speed,adress,longitude,latitude,capacity,description,navigation,rule_behaviour,fishing_equipment,more_information,delete_bt) VALUES ('baot 12','type12','5','2','200ks','230km','adr12',23.3333,54.2222,'capacity 12','description12','navitagtion12','rule_behaviour12','fishing_eqp12','more_info12',false);


--BOAT COMPLAINT
INSERT INTO boat_complaint(description,my_user_id,boat_id) VALUES ('opsi zalbe' , 2,2);

--BOAT OWNER COMPLAINT
INSERT INTO boat_owner_complaint(description,my_user_id,boat_owner_id) VALUES ('opsi zalbe' , 3,1);


-------------------------------------------------COTTAGE

--COTTAGE ACTION
INSERT INTO cottage_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('01-05-2022' , '03-05-2022', '5'  , 4500 , 'some more information');
INSERT INTO cottage_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('11-06-2022' , '18-06-2022', '1'  , 5500 , 'some more information .');
INSERT INTO cottage_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('09-06-2022' , '22-06-2022', '5'  , 15500 , 'some more information ...');
INSERT INTO cottage_action ( action_start, action_end, max_people_num,  price, more_information ) VALUES ('04-07-2022' , '08-07-2022', '2'  , 4000 , 'some more information ......');

--COTTAGE
INSERT INTO cottage (name,adress,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Brea Cottage','Wilson 4',23.3556,23.7777 , 'Description 2332',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,adress,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Swiiss','Street 21' ,23.3556,23.7777, 'Description 212',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,adress,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Lea Araptment','Street Lea',23.3556,23.7777 , 'Description 2grfgf',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,adress,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Brea 2','Wilson 51',23.3556,23.7777 , 'Description 2wqee',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,adress,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Brea ','Wilson 4' ,23.3556,23.7777, 'Description 2332',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,adress,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Swiiss 22','Street 21',23.3556,23.7777 , 'Description 212',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,adress,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Lea Dep','Street Lea' ,23.3556,23.7777, 'Description 2grfgf',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,adress,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Brea Corp','Wilson 51' ,23.3556,23.7777, 'Description 2wqee',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,adress,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Cottage','Wilson 4',23.3556,23.7777 , 'Description 2332',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,adress,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Swiiss4','Street 21' ,23.3556,23.7777, 'Description 212',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,adress,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Lea Aggg2','Street Lea',23.3556,23.7777 , 'Description 2grfgf',4,3,'rule behaviour','more informations',false);
INSERT INTO cottage (name,adress,longitude,latitude,description,number_of_room,number_of_bed_per_room,rule_behaviour,more_information,delete_ctg) VALUES ('Brea 256','Wilson 51',23.3556,23.7777 , 'Description 2wqee',4,3,'rule behaviour','more informations',false);


--COTTAGE IMAGE
INSERT INTO cottage_image ( name , image_byte) VALUES ('cottage_1' , lo_import('C:/tmp/cottage1.jpg'));
INSERT INTO cottage_image ( name , image_byte) VALUES ('cottage_2' , lo_import('C:/tmp/cottage2.jpg'));
INSERT INTO cottage_image ( name , image_byte) VALUES ('cottage_2' , lo_import('C:/tmp/cottage3.jpg'));

--COTTAGE COTTAGE IMAGE
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (1,1);
INSERT INTO cottage_cottage_images(cottage_id , cottage_images_id) VALUES (2,1);

--COTTAGE MARK
INSERT INTO cottage_mark (mark) VALUES (4);
INSERT INTO cottage_mark (mark) VALUES (4);
INSERT INTO cottage_mark (mark) VALUES (2);
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


--COTTAGE COMPLAINT
INSERT INTO cottage_complaint(description,my_user_id,cottage_id) VALUES ('opsi zalbe' , 1,1);

--COTTAGE OWNER COMPLAINT
INSERT INTO cottage_owner_complaint(description,my_user_id,cottage_owner_id) VALUES ('opsi zalbe' , 1,1);


--DELETE REQUEST
INSERT INTO delete_request (description,my_user_id) VALUES ('Obrisi mi nalog' , 1);

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

--INSTRUCTOR MARKS
INSERT INTO instructor_marks(instructor_id,marks_id) VALUES (1,1);


--MY USER ADVENTURES
INSERT INTO my_user_adventures(my_users_id,adventures_id) VALUES (1,1);

--MY USER BOATS
INSERT INTO my_user_boats(my_users_id,boats_id) VALUES (1,2);

--MY USER COTTAGES
INSERT INTO my_user_cottages(my_users_id,cottages_id) VALUES (1,2);