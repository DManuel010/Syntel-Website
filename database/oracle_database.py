import os, sys
import cx_Oracle


def build_db():
	queries = list()


	##############
	#    FOOD    #
	##############

	query_food = '''CREATE TABLE Food (
						foodID INT NOT NULL PRIMARY KEY,
						name VARCHAR(25) NOT NULL,
						foodGroup VARCHAR(25) NOT NULL,
						price DECIMAL(10,3) NOT NULL,
						description VARCHAR(50),
						stock INT NOT NULL,
						CONSTRAINT UC_Food UNIQUE (name, foodGroup, price)
					);'''

	queries.append(query_food)


	##################
	#    LOCATION    #
	##################

	query_location = '''CREATE TABLE Location (
							locationID INT NOT NULL PRIMARY KEY,
							country VARCHAR(25) NOT NULL,
							state VARCHAR(25) NOT NULL,
							city VARCHAR(25) NOT NULL,
							streetNum VARCHAR(25) NOT NULL,
							roomNum VARCHAR(25),
							zip VARCHAR(25) NOT NULL,
							CONSTRAINT UC_Location UNIQUE (country, state, city, streetNum, roomNum, zip)
						);'''

	queries.append(query_location)


	###############
	#    COMBO    #
	###############

	query_combo = '''CREATE TABLE Combo (
						comboID INT NOT NULL PRIMARY KEY,
						name VARCHAR(25),
						price DECIMAL(10,3) NOT NULL,
						description VARCHAR(25),
						CONSTRAINT UC_Combo UNIQUE (name, price, description)
					);'''

	queries.append(query_combo)


	####################
	#    COMBO_FOOD    #
	####################

	query_combo_food = '''CREATE TABLE ComboFood (
							comboFoodID INT NOT NULL PRIMARY KEY,
							foodID INT NOT NULL REFERENCES Food(foodID),
							comboID INT NOT NULL REFERENCES Combo(comboID),
							CONSTRAINT UC_ComboFood UNIQUE (foodID, comboID)
						);'''

	queries.append(query_combo_food)


	#################
	#    PAYMENT    #
	#################

	query_payment = '''CREATE TABLE Payment (
							paymentID INT NOT NULL PRIMARY KEY,
							type VARCHAR(25) NOT NULL,
							amount DECIMAL(10,3) NOT NULL,
							datePaid DATE NOT NULL
						);'''

	queries.append(query_payment)


	###############
	#    LOGIN    #
	###############

	query_login = '''CREATE TABLE Login (
						loginID INT NOT NULL PRIMARY KEY,
						username VARCHAR(25) NOT NULL UNIQUE,
						password VARCHAR(25) NOT NULL
					);'''

	queries.append(query_login)


	##################
	#    EMPLOYEE    #
	##################

	query_employee = '''CREATE TABLE Employee (
							employeeID INT NOT NULL PRIMARY KEY,
							firstName VARCHAR(25) NOT NULL,
							lastName VARCHAR(25) NOT NULL,
							email VARCHAR(25) NOT NULL UNIQUE,
							hireDate DATE NOT NULL,
							title VARCHAR(25) NOT NULL,
							loginID INT NOT NULL UNIQUE REFERENCES Login(loginID),
							phoneNumber VARCHAR(25) NOT NULL,
							workAddrID INT NOT NULL REFERENCES Location(locationID),
							homeAddrID INT NOT NULL REFERENCES Location(locationID),
							lastLogin DATE NOT NULL
						);'''

	queries.append(query_employee)


	##############
	#    CARD    #
	##############

	query_card = '''CREATE TABLE Card (
						cardID INT NOT NULL PRIMARY KEY,
						name VARCHAR(25) NOT NULL,
						cardNumber INT NOT NULL,
						expirationDate DATE NOT NULL,
						cvv INT NOT NULL,
						type VARCHAR(25) NOT NULL,
						billingAddrID INT NOT NULL REFERENCES Location(locationID),
						CONSTRAINT UC_Card UNIQUE (name, cardNumber, expirationDate, cvv, type)
					);'''

	queries.append(query_card)


	##################
	#    CUSTOMER    #
	##################

	query_customer = '''CREATE TABLE Customer (
							customerID INT NOT NULL PRIMARY KEY,
							firstName VARCHAR(25) NOT NULL,
							lastName VARCHAR(25) NOT NULL,
							email VARCHAR(25) NOT NULL,
							phoneNumber VARCHAR(25) NOT NULL,
							loginID INT NOT NULL UNIQUE REFERENCES Login(loginID),
							dateOfBirth DATE NOT NULL,
							homeAddrID INT NOT NULL REFERENCES Location(locationID),
							cardID INT REFERENCES Card(cardID),
							dateOfRegister DATE NOT NULL,
							lastLogin DATE NOT NULL
						);'''

	queries.append(query_customer)


	################
	#    ORDERS    #
	################

	query_order = '''CREATE TABLE Orders (
						orderID INT NOT NULL PRIMARY KEY,
						employeeID INT NOT NULL REFERENCES Employee(employeeID),
						customerID INT NOT NULL REFERENCES Customer(customerID),
						cost DECIMAL(10,3) NOT NULL,
						paymentID INT NOT NULL REFERENCES Payment(paymentID),
						deliveryAddrID INT NOT NULL REFERENCES Location(locationID),
						orderDate DATE NOT NULL,
						expectedDate DATE NOT NULL,
						deliveryDate DATE,
						note VARCHAR(25)
					);'''

	queries.append(query_order)


	####################
	#    FOOD_ORDER    #
	####################

	query_food_order = '''CREATE TABLE FoodOrder (
							foodOrderID INT NOT NULL PRIMARY KEY,
							orderID INT NOT NULL REFERENCES Orders(orderID),
							foodID INT NOT NULL REFERENCES Food(foodID),
							quantity INT NOT NULL
						);'''

	queries.append(query_food_order)

	return queries


def destroy_db():
	queries = list()

	query_food = '''DROP TABLE Food;'''
	queries.append(query_food)

	query_location = '''DROP TABLE Location;'''
	queries.append(query_location)

	query_combo = '''DROP TABLE Combo;'''
	queries.append(query_combo)

	query_combo_food = '''DROP TABLE ComboFood;'''
	queries.append(query_combo_food)

	query_payment = '''DROP TABLE Payment;'''
	queries.append(query_payment)

	query_login = '''DROP TABLE Login;'''
	queries.append(query_login)

	query_employee = '''DROP TABLE Employee;'''
	queries.append(query_employee)

	query_card = '''DROP TABLE Card;'''
	queries.append(query_card)

	query_customer = '''DROP TABLE Customer;'''
	queries.append(query_customer)

	query_orders = '''DROP TABLE Orders;'''
	queries.append(query_order)

	query_food_order = '''DROP TABLE FoodOrder;'''
	queries.append(query_food_order)

	return queries




def main():
	username = sys.argv[1]
	password = sys.argv[2]

	conn = cx_Oracle.connect('{}/{}'.format(username, password))
	
	cursor = conn.cursor()
	drop_queries = drop_db()
	build_queries = build_db()

	for query in drop_queries:
		cursor.execute(query)

	for query in build_queries:
		cursor.execut(query)

	conn.commit()
	cursor.close()
	conn.close()

	print("Database created successfully.")
		
if __name__ == "__main__":
	main()