import os, sys
import mysql.connector


def get_queries():
	queries = list()


	##############
	#    FOOD    #
	##############

	query_food = '''CREATE TABLE Food (
						foodID INT AUTO_INCREMENT,
						name VARCHAR(100) NOT NULL,
						foodGroup VARCHAR(100) NOT NULL,
						price DECIMAL(10,3) NOT NULL,
						description VARCHAR(100),
						stock INT NOT NULL,
						PRIMARY KEY (foodID),
						CONSTRAINT UC_Food UNIQUE (name, foodGroup, price)
					);'''

	queries.append(query_food)


	##################
	#    LOCATION    #
	##################

	query_location = '''CREATE TABLE Location (
							locationID INT AUTO_INCREMENT,
							country VARCHAR(100) NOT NULL,
							state VARCHAR(100) NOT NULL,
							city VARCHAR(100) NOT NULL,
							streetNum VARCHAR(100) NOT NULL,
							roomNum VARCHAR(100),
							zip VARCHAR(100) NOT NULL,
							PRIMARY KEY (locationID),
							CONSTRAINT UC_Location UNIQUE (country, state, city, streetNum, roomNum, zip)
						);'''

	queries.append(query_location)


	###############
	#    COMBO    #
	###############

	query_combo = '''CREATE TABLE Combo (
						comboID INT AUTO_INCREMENT,
						name VARCHAR(100),
						price DECIMAL(10) NOT NULL,
						description VARCHAR(100),
						PRIMARY KEY (comboID),
						CONSTRAINT UC_Combo UNIQUE (name, price, description)
					);'''

	queries.append(query_combo)


	####################
	#    COMBO_FOOD    #
	####################

	query_combo_food = '''CREATE TABLE ComboFood (
							comboFoodID INT AUTO_INCREMENT,
							foodID INT NOT NULL,
							comboID INT NOT NULL,
							PRIMARY KEY (comboFoodID),
							FOREIGN KEY (foodID) REFERENCES Food(foodID),
							FOREIGN KEY (comboID) REFERENCES Combo(comboID),
							CONSTRAINT UC_ComboFood UNIQUE (foodID, comboID)
						);'''

	queries.append(query_combo_food)


	#################
	#    PAYMENT    #
	#################

	query_payment = '''CREATE TABLE Payment (
							paymentID INT AUTO_INCREMENT,
							type VARCHAR(100) NOT NULL,
							amount DECIMAL(10) NOT NULL,
							datePaid DATETIME NOT NULL,
							PRIMARY KEY (paymentID)
						);'''

	queries.append(query_payment)


	###############
	#    LOGIN    #
	###############

	query_login = '''CREATE TABLE Login (
						loginID INT AUTO_INCREMENT,
						username VARCHAR(100) NOT NULL,
						password VARCHAR(100) NOT NULL,
						PRIMARY KEY (loginID),
						UNIQUE (username)
					);'''

	queries.append(query_login)


	##################
	#    EMPLOYEE    #
	##################

	query_employee = '''CREATE TABLE Employee (
							employeeID INT AUTO_INCREMENT,
							firstName VARCHAR(100) NOT NULL,
							lastName VARCHAR(100) NOT NULL,
							email VARCHAR(100) NOT NULL,
							hireDate DATETIME NOT NULL,
							title VARCHAR(100) NOT NULL,
							loginID INT NOT NULL,
							phoneNumber VARCHAR(100) NOT NULL,
							workAddrID INT NOT NULL,
							homeAddrID INT NOT NULL,
							lastLogin DATETIME NOT NULL,
							PRIMARY KEY (employeeID),
							FOREIGN KEY (loginID) REFERENCES Login(loginID),
							FOREIGN KEY (workAddrID) REFERENCES Location(locationID),
							FOREIGN KEY (homeAddrID) REFERENCES Location(locationID),
							UNIQUE (loginID),
							UNIQUE (email)
						);'''

	queries.append(query_employee)


	##############
	#    CARD    #
	##############

	query_card = '''CREATE TABLE Card (
						cardID INT AUTO_INCREMENT,
						name VARCHAR(100) NOT NULL,
						cardNumber INT NOT NULL,
						expirationDate DATETIME NOT NULL,
						cvv INT NOT NULL,
						type VARCHAR(100) NOT NULL,
						billingAddrID INT NOT NULL,
						PRIMARY KEY (cardID),
						FOREIGN KEY (billingAddrID) REFERENCES Location(locationID),
						CONSTRAINT UC_Card UNIQUE (name, cardNumber, expirationDate, cvv, type)
					);'''

	queries.append(query_card)


	##################
	#    CUSTOMER    #
	##################

	query_customer = '''CREATE TABLE Customer (
							customerID INT AUTO_INCREMENT,
							firstName VARCHAR(100) NOT NULL,
							lastName VARCHAR(100) NOT NULL,
							email VARCHAR(100) NOT NULL,
							loginID INT NOT NULL,
							dateOfBirth DATETIME NOT NULL,
							homeAddrID INT NOT NULL,
							cardID INT,
							dateOfRegister DATETIME NOT NULL,
							lastLogin DATETIME NOT NULL,
							PRIMARY KEY (customerID),
							FOREIGN KEY (loginID) REFERENCES Login(loginID),
							FOREIGN KEY (homeAddrID) REFERENCES Location(locationID),
							FOREIGN KEY (cardID) REFERENCES Card(cardID),
							UNIQUE (loginID)
						);'''

	queries.append(query_customer)


	###############
	#    ORDER    #
	###############

	query_order = '''CREATE TABLE Orders (
						orderID INT AUTO_INCREMENT,
						employeeID INT NOT NULL,
						customerID INT NOT NULL,
						cost DECIMAL(10,3) NOT NULL,
						paymentID INT NOT NULL,
						deliveryAddrID INT NOT NULL,
						orderDate DATETIME NOT NULL,
						expectedDate DATETIME NOT NULL,
						deliveryDate DATETIME,
						note VARCHAR(100),
						PRIMARY KEY (orderID),
						FOREIGN KEY (employeeID) REFERENCES Employee(employeeID),
						FOREIGN KEY (customerID) REFERENCES Customer(customerID),
						FOREIGN KEY (paymentID) REFERENCES Payment(paymentID),
						FOREIGN KEY (deliveryAddrID) REFERENCES Location(locationID)
					);'''

	queries.append(query_order)


	####################
	#    FOOD_ORDER    #
	####################

	query_food_order = '''CREATE TABLE FoodOrder (
							foodOrderID INT AUTO_INCREMENT,
							orderID INT NOT NULL,
							foodID INT NOT NULL,
							quantity INT NOT NULL,
							PRIMARY KEY (foodOrderID),
							FOREIGN KEY (orderID) REFERENCES Orders(orderID),
							FOREIGN KEY (foodID) REFERENCES Food(foodID)
						);'''

	queries.append(query_food_order)

	return queries

def build_db():
	username = sys.argv[1]
	password = sys.argv[2]

	conn = mysql.connector.connect(
				host='localhost',
				user=username,
				password=password
			)
	
	cursor = conn.cursor()

	query_create = '''CREATE DATABASE restaurant;'''
	cursor.execute(query_create)

	conn.database = 'restaurant'
	queries = get_queries()

	for query in queries:
		cursor.execute(query)

	conn.commit()
	cursor.close()
	conn.close()

	print("Database created successfully.")

		
if __name__ == "__main__":
	build_db()