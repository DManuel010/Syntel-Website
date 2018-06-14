insert into food 
	values (0, 'Eggs', 'Breakfast', 3.0, '1000 things', 999, 'eggs.jpg', 1);
insert into food 
	values (1, 'Sandwich', 'Lunch', 6.0, 'bread and things', 999, 'sandwich.jpg', 1);
insert into food 
	values (2, 'Steak', 'Dinner', 9.0, 'wow', 999, 'steak.jpg', 1);
insert into food 
	values (3, 'Pan-Friend Chicken', 'Dinner', 50.0, 'a timeless classic', 999, 'pan-friendChicken.jpg', 1);

insert into customer
	values (0,'Cire', 'Sinrak', 'e@k.com',1234567890,'07-feb-1994','01-jan-13 03:14:07','01-jan-13 03:14:07', 'Œ?˜ä6',0123456789);

insert into location 
	values (0, 0, 'Canada', 'Ontario', 'Toronto', '1 Bloor', ' ', 'a1b2c3');
insert into location 
	values (1, 0, 'Canada', 'Quebec', 'Montreal', '1 Rue', ' ', 'd4e5f6');
insert into location 
	values (2, 0, 'USA', 'Arizona', 'Phoenix', '1 Deer Valley', ' ', '12345');

insert into combo 
	values (0, 'small combo', 5.0, 'eggs and sandwich');
insert into combo 
	values (1, 'medium combo', 10.0, 'eggs and steak');
insert into combo 
	values (2, 'large combo', 15.0, 'eggs, steak, sandwich');

insert into combofood
	values (0,0,0);
insert into combofood
	values (1,1,0);
insert into combofood
	values (2,1,1);
insert into combofood
	values (3,2,0);
insert into combofood
	values (4,2,1);
insert into combofood
	values (5,2,2);


insert into payment
	values (0,'paypal',5.0,'07-jan-18');
insert into payment
	values (1,'card',15.0,'08-jan-18');
insert into payment
	values (2,'cash',25.0,'09-jan-18');

insert into employee
	values (0,'Eric', 'Karnis', 'eric@karnis.com','07-jan-18','Œ?˜ä6',1234567890,0,0,'07-may-18');

insert into employee
	values(1114298631, 'Jessica', 'Jones','jessica@gmail.com','07-jan-18','admin',1234567890,0,0,'07-may-18');

insert into orders
	values (0,0,30.0,0,0,'07-jan-18','07-jan-18','07-jan-18','make it good ok');

insert into foodorder
	values(0,0,0,10);
	



disconnect;
connect cholla/cholla;