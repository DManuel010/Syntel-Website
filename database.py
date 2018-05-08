import os, sys
import cx_Oracle as cx

def build_db():
	username = sys.argv[1]
	password = sys.argv[2]
	queries = list()

	db = cx.connect(username, password)
	
	cursor = db.cursor()

	query_db = "CREATE DATABASE restaurant;"
	queries.append(query_create_db)


	##############
	#    FOOD    #
	##############

	query_food = '''CREATE TABLE Food (
						foodID INT NOT NULL PRIMARY KEY,
						name VARCHAR(255) NOT NULL,
						group VARCHAR(255) NOT NULL,
						price DECIMAL(10) NOT NULL,
						description VARCHAR(255),
						stock INT NOT NULL
					);'''

	query_food_seq = '''CREATE SEQUENCE seq_food
						MINVALUE 1
						START WITH 1
						INCREMENT BY 1
						CACHE 10;'''

	queries.append(query_food)
	queries.append(query_food_seq)


	##################
	#    LOCATION    #
	##################

	query_location = '''CREATE TABLE Location (
							locationID INT NOT NULL PRIMARY KEY,
							country VARCHAR(255) NOT NULL,
							state VARCHAR(255) NOT NULL,
							city VARCHAR(255) NOT NULL,
							streetNum VARCHAR(255) NOT NULL,
							roomNum VARCHAR(255),
							zip VARCHAR(255) NOT NULL
						);'''

	query_location_seq = '''CREATE SEQUENCE seq_location
							MINVALUE 1
							START WITH 1
							INCREMENT BY 1
							CACHE 10;'''

	queries.append(query_location)
	queries.append(query_location_seq)


	###############
	#    COMBO    #
	###############

	query_combo = '''CREATE TABLE Combo (
						comboID INT NOT NULL PRIMARY KEY,
						name VARCHAR(255),
						price DECIMAL(10) NOT NULL,
						description VARCHAR(255)
					);'''

	query_combo_seq = '''CREATE SEQUENCE seq_combo
						MINVALUE 1
						START WITH 1
						INCREMENT BY 1
						CACHE 10;'''

	queries.append(query_combo)
	queries.append(query_combo_seq)


	####################
	#    COMBO_FOOD    #
	####################

	query_combo_food = '''CREATE TABLE ComboFood (
							comboFoodID INT NOT NULL PRIMARY KEY,
							foodID INT NOT NULL FOREIGN KEY REFERENCES Food(foodID),
							comboID INT NOT NULL FOREIGN KEY REFERENCES Combo(comboID)
						);'''

	query_combo_food_seq = '''CREATE SEQUENCE seq_combo_food
							MINVALUE 1
							START WITH 1
							INCREMENT BY 1
							CACHE 10;'''

	queries.append(query_combo_food)
	queries.append(query_combo_food_seq)


	#################
	#    PAYMENT    #
	#################

	query_payment = '''CREATE TABLE Payment (
							paymentID INT NOT NULL PRIMARY KEY,
							type VARCHAR(255) NOT NULL,
							amount DECIMAL(10) NOT NULL,
							datePaid DATETIME NOT NULL
						);'''

	query_payment_seq = '''CREATE SEQUENCE seq_payment
							MINVALUE 1
							START WITH 1
							INCREMENT BY 1
							CACHE 10;'''

	queries.append(query_payment)
	queries.append(query_payment_seq)


	###############
	#    LOGIN    #
	###############

	query_login = '''CREATE TABLE Login (
						loginID INT NOT NULL PRIMARY KEY,
						username VARCHAR(255) NOT NULL UNIQUE,
						password VARCHAR(255) NOT NULL,
					);'''

	query_login_seq = '''CREATE SEQUENCE seq_login
						MINVALUE 1
						START WITH 1
						INCREMENT BY 1
						CACHE 10;'''

	queries.append(query_login)
	queries.append(query_login_seq)


	##################
	#    EMPLOYEE    #
	##################

	query_employee = '''CREATE TABLE Employee (
							employeeID INT NOT NULL PRIMARY KEY,
							firstName VARCHAR(255) NOT NULL,
							lastName VARCHAR(255) NOT NULL,
							email VARCHAR(255) NOT NULL UNIQUE,
							hireDate DATETIME NOT NULL,
							title VARCHAR(255) NOT NULL,
							loginID INT NOT NULL UNIQUE FOREIGN KEY REFERENCES Login(loginID),
							phoneNumber VARCHAR(255) NOT NULL,
							workAddrID INT NOT NULL FOREIGN KEY REFERENCES Location(locationID),
							homeAddrID INT NOT NULL FOREIGN KEY REFERENCES Location(locationID),
							lastLogin DATETIME NOT NULL
						);'''

	query_employee_seq = '''CREATE SEQUENCE seq_employee
							MINVALUE 1
							START WITH 1
							INCREMENT BY 1
							CACHE 10;'''

	queries.append(query_employee)
	queries.append(query_employee_seq)


	##############
	#    CARD    #
	##############

	query_card = '''CREATE TABLE Card (
						cardID INT NOT NULL PRIMARY KEY,
						name VARCHAR(255) NOT NULL,
						cardNumber INT NOT NULL,
						expirationDate DATETIME NOT NULL,
						cvv INT NOT NULL,
						type VARCHAR(255) NOT NULL,
						billingAddrID INT NOT NULL FOREIGN KEY REFERENCES Location(locationID)
						CONSTRAINT UC_Card UNIQUE (name, cardNumber, expirationDate, csv, type)
					);'''

	query_card_seq = '''CREATE SEQUENCE seq_card
						MINVALUE 1
						START WITH 1
						INCREMENT BY 1
						CACHE 10;'''

	queries.append(query_card)
	queries.append(query_card_seq)


	##################
	#    CUSTOMER    #
	##################

	query_customer = '''CREATE TABLE Customer (
							customerID INT NOT NULL PRIMARY KEY,
							firstName VARCHAR(255) NOT NULL,
							lastName VARCHAR(255) NOT NULL,
							email VARCHAR(255) NOT NULL,
							loginID INT NOT NULL UNIQUE FOREIGN KEY REFERENCES Login(loginID),
							dateOfBirth DATETIME NOT NULL,
							homeAddrID INT NOT NULL FOREIGN KEY REFERENCES Location(locationID),
							cardID INT FOREIGN KEY REFERENCES Card(cardID),
							dateOfRegister DATETIME NOT NULL,
							lastLogin DATETIME NOT NULL
						);'''

	query_customer_seq = '''CREATE SEQUENCE seq_customer
							MINVALUE 1
							START WITH 1
							INCREMENT BY 1
							CACHE 10;'''

	queries.append(query_customer)
	queries.append(query_customer_seq)


	###############
	#    ORDER    #
	###############

	query_order = '''CREATE TABLE Order (
						orderID INT NOT NULL PRIMARY KEY,
						employeeID INT NOT NULL FOREIGN KEY REFERENCES Employee(employeeID),
						customerID INT NOT NULL FOREIGN KEY REFERENCES Customer(customerID),
						cost DECIMAL(10) NOT NULL,
						paymentID INT NOT NULL FOREIGN KEY REFERENCES Payment(paymentID),
						deliveryAddrID INT NOT NULL FOREIGN KEY REFERENCES Location(locationID),
						orderDate DATETIME NOT NULL,
						expectedDate DATETIME NOT NULL,
						deliveryDate DATETIME,
						note VARCHAR(255)
					);'''

	query_order_seq = '''CREATE SEQUENCE seq_order
						MINVALUE 1
						START WITH 1
						INCREMENT BY 1
						CACHE 10;'''

	queries.append(query_order)
	queries.append(query_order_seq)


	####################
	#    FOOD_ORDER    #
	####################

	query_food_order = '''CREATE TABLE FoodOrder (
							foodOrderID INT NOT NULL PRIMARY KEY,
							orderID INT NOT NULL FOREIGN KEY REFERENCES Order(orderID),
							foodID INT NOT NULL FOREIGN KEY REFERENCES Food(foodID),
							quantity INT NOT NULL
						);'''

	query_food_order_seq = '''CREATE SEQUENCE seq_food_order
								MINVALUE 1
								START WITH 1
								INCREMENT BY 1
								CACHE 10;'''


	for query in queries:
		cursor.execute(query)

		
if __name__ == "__main__":
	build_db()