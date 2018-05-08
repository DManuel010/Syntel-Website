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
	queries.append(query_food)

	query_food_seq = '''CREATE SEQUENCE seq_food
						MINVALUE 1
						START WITH 1
						INCREMENT BY 1
						CACHE 10;'''
	queries.append(query_food_seq)


	##################
	#    LOCATION    #
	##################

	query_location = '''CREATE TABLE Location (
							foodID INT NOT NULL PRIMARY KEY,
							name VARCHAR(255) NOT NULL,
							group VARCHAR(255) NOT NULL,
							price DECIMAL(10) NOT NULL,
							description VARCHAR(255),
							stock INT NOT NULL
						);'''
	queries.append(query_location)

	query_location_seq = '''CREATE SEQUENCE seq_location
							MINVALUE 1
							START WITH 1
							INCREMENT BY 1
							CACHE 10;'''
	queries.append(query_location_seq)


	##################
	#    EMPLOYEE    #
	##################

	query_employee = '''CREATE TABLE Employee (
							employeeID INT NOT NULL PRIMARY KEY,
							firstName VARCHAR(255) NOT NULL,
							lastName VARCHAR(255) NOT NULL,
							hireDate DATETIME NOT NULL,
							terminateDate DATETIME NOT NULL,
							loginID 
						);'''



	for query in queries:
		cursor.execute(query)

		
if __name__ == "__main__":
	build_db()