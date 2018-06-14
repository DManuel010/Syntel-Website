CREATE TABLE Food (
		foodID INT NOT NULL PRIMARY KEY,
		name VARCHAR(25) NOT NULL,
		foodGroup VARCHAR(25) NOT NULL,
		price DECIMAL(10,3) NOT NULL,
		description VARCHAR(50),
		stock INT NOT NULL,
		image VARCHAR(300) NOT NULL,
		active INT NOT NULL,
		CONSTRAINT UC_Food UNIQUE (name, foodGroup, price),
		CONSTRAINT check_active CHECK (active IN (0, 1))
);

CREATE TABLE Customer (
	customerID INT NOT NULL PRIMARY KEY,
	firstName VARCHAR(25) NOT NULL,
	lastName VARCHAR(25) NOT NULL,
	email VARCHAR(255) NOT NULL,
	phoneNumber VARCHAR(25) NOT NULL,
	dateOfBirth DATE NOT NULL,
	dateOfRegister TIMESTAMP NOT NULL,
	lastLogin TIMESTAMP NOT NULL,
	PASS VARCHAR2(4000),
	MOBILE_NUMBER VARCHAR2(25)
);
		
CREATE TABLE Location (
	locationID INT NOT NULL PRIMARY KEY,
	customerID INT NOT NULL REFERENCES Customer(customerID),
	country VARCHAR(25) NOT NULL,
	state VARCHAR(25) NOT NULL,
	city VARCHAR(25) NOT NULL,
	streetNum VARCHAR(25) NOT NULL,
	roomNum VARCHAR(25),
	zip VARCHAR(25) NOT NULL
);

CREATE TABLE Combo (
	comboID INT NOT NULL PRIMARY KEY,
	name VARCHAR(25),
	price DECIMAL(10,3) NOT NULL,
	description VARCHAR(25),
	CONSTRAINT UC_Combo UNIQUE (name, price, description)
);

CREATE TABLE ComboFood (
	comboFoodID INT NOT NULL PRIMARY KEY,
	foodID INT NOT NULL REFERENCES Food(foodID),
	comboID INT NOT NULL REFERENCES Combo(comboID)
);

CREATE TABLE Payment (
	paymentID INT NOT NULL PRIMARY KEY,
	type VARCHAR(25) NOT NULL,
	amount DECIMAL(10,3) NOT NULL,
	datePaid DATE NOT NULL
);

-- CREATE TABLE Login (
	-- loginID INT NOT NULL PRIMARY KEY,
	-- username VARCHAR(25) NOT NULL UNIQUE,
	-- password VARCHAR(25) NOT NULL,
	-- type VARCHAR(25) NOT NULL
-- );

CREATE TABLE Employee (
	employeeID INT NOT NULL PRIMARY KEY,
	firstName VARCHAR(25) NOT NULL,
	lastName VARCHAR(25) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	hireDate DATE NOT NULL,
	title VARCHAR(25) NOT NULL,
	phoneNumber VARCHAR(25) NOT NULL,
	workAddrID INT NOT NULL REFERENCES Location(locationID),
	homeAddrID INT NOT NULL REFERENCES Location(locationID),
	lastLogin DATE NOT NULL,
	password VARCHAR2(4000)
);

CREATE TABLE Orders (
	orderID INT NOT NULL PRIMARY KEY,
	customerID INT NOT NULL REFERENCES Customer(customerID),
	cost DECIMAL(10,3) NOT NULL,
	paymentID INT NOT NULL REFERENCES Payment(paymentID),
	deliveryAddrID INT NOT NULL REFERENCES Location(locationID),
	orderDate DATE NOT NULL,
	expectedDate DATE NOT NULL,
	deliveryDate DATE,
	note VARCHAR(250)
);

CREATE TABLE FoodOrder (
	foodOrderID INT NOT NULL PRIMARY KEY,
	orderID INT NOT NULL REFERENCES Orders(orderID),
	foodID INT NOT NULL REFERENCES Food(foodID),
	quantity INT NOT NULL
);