-- PROCEDURE USED TO INSERT NEW CUSTOMERS

CREATE OR REPLACE PROCEDURE SP_INSERT_NEW_CUSTOMER
(CUST_ID INT, F_NAME VARCHAR2,L_NAME VARCHAR2,EMAIL VARCHAR2, PHONE VARCHAR2, 
DOB DATE, PASS VARCHAR2, MOBILE VARCHAR2) AS
BEGIN
  INSERT INTO CUSTOMER(CUSTOMERID, FIRSTNAME,LASTNAME, EMAIL, PHONENUMBER,
  DATEOFBIRTH, DATEOFREGISTER, LASTLOGIN, PASS, MOBILE_NUMBER)
    VALUES(CUST_ID,F_NAME,L_NAME,EMAIL,PHONE,TO_DATE(DOB,'dd-MM-yyyy'),SYSTIMESTAMP,SYSTIMESTAMP,
    PASS, MOBILE);
END;

-- INSERT NEW ORDERS 

CREATE OR REPLACE PROCEDURE SP_INSERT_NEW_ORDER(orderid varchar2, employeeid number, customerid number, 
price number, paymentid number, deliveryaddrid number, orderdate varchar2, expecteddate varchar2, 
deliverydate varchar2, note varchar2)
AS
BEGIN
INSERT INTO ORDERS
VALUES (orderid, employeeid, customerid, price, paymentid, deliveryaddrid, orderdate, 
expecteddate, deliverydate, note);
END;

-- DELETE ORDER

CREATE OR REPLACE PROCEDURE SP_DELETE_ORDER(orderid number)
AS
BEGIN
DELETE FROM ORDERS
WHERE ORDERID = orderid;
END;

-- INSERT NEW FOOD

CREATE OR REPLACE PROCEDURE SP_INSERT_NEW_FOOD(foodid varchar2, foodname varchar2, 
foodgroup varchar2, price number, description varchar2, stock number)
AS
BEGIN
INSERT INTO FOOD
VALUES (foodid, foodname, foodgroup, price, description, stock);
END;

-- DELETE FOOD

CREATE OR REPLACE PROCEDURE SP_DELETE_FOOD(foodid number)
AS
BEGIN
DELETE FROM FOOD
WHERE FOODID = foodid;
END;

--INSERT NEW LOCATION

CREATE OR REPLACE PROCEDURE SP_INSERT_NEW_LOCATION(locationid number, country varchar2, 
states varchar2, city varchar2, street varchar2, room varchar2, zip varchar2)
AS
BEGIN
INSERT INTO LOCATION
VALUES (locationid, country, states, city, street, room, zip);
END;

--DELETE LOCATION

CREATE OR REPLACE PROCEDURE SP_DELETE_LOCATION(locationid number)
AS
BEGIN
DELETE FROM LOCATION
WHERE LOCATIONID = locationid;
END;