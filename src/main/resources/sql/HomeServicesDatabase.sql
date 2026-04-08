/*-------------------------------------------------------------------------------------*/
DROP DATABASE HomeServicesDB;
CREATE DATABASE HomeServicesDB;
USE HomeServicesDB;

/*-------------------------------------------------------------------------------------*/
DROP SCHEMA HomeServicesDB;
CREATE SCHEMA HomeServicesDB;
USE HomeServicesDB;

/*-------------------------------------------------------------------------------------*/
--Create tblCustomer

CREATE TABLE HomeServicesDB.tblCustomer (
  CustomerID INT NOT NULL AUTO_INCREMENT,
  FirstName VARCHAR(45) NOT NULL,
  LastName VARCHAR(45) NOT NULL,
  StreetAddress1 VARCHAR(45) NOT NULL,
  StreetAddress2 VARCHAR(45) NOT NULL,
  City VARCHAR(45) NOT NULL,
  Country VARCHAR(45) NOT NULL,
  MobilePhone VARCHAR(45) NOT NULL,
  Email VARCHAR(45) NOT NULL,
  Password VARCHAR(45) NOT NULL,
  AccountType VARCHAR(45) NOT NULL,
  Active TINYINT NOT NULL,
  PRIMARY KEY (CustomerID),
  UNIQUE INDEX CustomerID_UNIQUE (CustomerID ASC) VISIBLE);

/*-------------------------------------------------------------------------------------*/
INSERT INTO HomeServicesDB.tblCustomer (CustomerID, FirstName, LastName, StreetAddress1, StreetAddress2, City, Country, MobilePhone, Email, Password, AccountType, Active)

VALUES
	(null, "Aaron", "Kalloo", "#7 Valley View Drive", "Pinto Road", "Arima", "Trinidad", "(868)717-8916", "aaronkalloo@gmail.com", "Pass", "Customer", 1),
    (null, "Darion", "Thomas", "#22 Orange Drive", "Santa Rosa", "Arima", "Trinidad", "(868)284-0091", "darionthomas@gmail.com", "Pass", "Customer", 1),
    (null, "Jonathan", "Bachan", "#55 Cresend Drive", "Long Circular Road", "San Fernando", "Trinidad", "(868)324-6213", "johnny@gmail.com", "Pass", "Customer", 1),
    (null, "Kimmy", "Khan", "#4 Bon Accord Drive", "Simple Road", "Scarborough", "Tobago", "(868)625-8821", "kimmykhan@gmail.com", "Pass", "Customer", 1),
    (null, "Selena", "Brown", "#107 Yellow Bird Drive", "Timberland Park", "Sangre Grande", "Trinidad", "(868)453-1298", "selena@gmail.com", "Pass", "Customer", 1),
;

/*-------------------------------------------------------------------------------------*/
  --Create tblEmployee

CREATE TABLE HomeServicesDB.tblEmployee (
EmployeeID INT NOT NULL AUTO_INCREMENT,
FirstName VARCHAR(45) NOT NULL,
LastName VARCHAR(45) NOT NULL,
StreetAddress1 VARCHAR(45) NOT NULL,
StreetAddress2 VARCHAR(45) NOT NULL,
City VARCHAR(45) NOT NULL,
Country VARCHAR(45) NOT NULL,
MobilePhone VARCHAR(45) NOT NULL,
Email VARCHAR(45) NOT NULL,
Password VARCHAR(45) NOT NULL,
AccountType VARCHAR(45) NOT NULL,
Active TINYINT NOT NULL,
PRIMARY KEY (EmployeeID),
UNIQUE INDEX EmployeeID_UNIQUE (EmployeeID ASC) VISIBLE);

/*-------------------------------------------------------------------------------------*/
INSERT INTO HomeServicesDB.tblEmployee (EmployeeID, FirstName, LastName, StreetAddress1, StreetAddress2, City, Country, MobilePhone, Email, Password, AccountType, Active)

VALUES
	(null, "Matthew", "Chandoo", "#18 Point Road", "Hich Estate", "Chagunas", "Trinidad", "(868)668-0198", "matty@gmail.com", "Pass", "Employee", 1),
    (null, "Renaldo", "Boodhoo", "#321 Jump Street", "Dememara Road", "Arima", "Trinidad", "(868)342-0012", "renaldo@gmail.com", "Pass", "Employee", 1),
    (null, "Amelia", "Terry", "#1 Kenny Drive", "Gold Street", "Bon Accord", "Tobago", "(868)782-8723", "amelia@gmail.com", "Pass", "Employee", 1),
    (null, "Roshan", "Babwah", "#34 Love Road", "Cupid Park", "Couva", "Trinidad", "(868)224-0819", "roshan@gmail.com", "Pass", "Employee", 1),
    (null, "Maile", "Harry", "#76 Red Road", "The Plantations", "Scarborough", "Tobago", "(868)771-0673", "malie@gmail.com", "Pass", "Employee", 1),
;

/*-------------------------------------------------------------------------------------*/
 --Create tblAdmin

  CREATE TABLE HomeServicesDB.tblAdmin (
  AdminID INT NOT NULL AUTO_INCREMENT,
  FirstName VARCHAR(45) NOT NULL,
  LastName VARCHAR(45) NOT NULL,
  StreetAddress1 VARCHAR(45) NOT NULL,
  StreetAddress2 VARCHAR(45) NOT NULL,
  City VARCHAR(45) NOT NULL,
  Country VARCHAR(45) NOT NULL,
  MobilePhone VARCHAR(45) NOT NULL,
  Email VARCHAR(45) NOT NULL,
  Password VARCHAR(45) NOT NULL,
  AccountType VARCHAR(45) NOT NULL,
  Active TINYINT NOT NULL,
  PRIMARY KEY (AdminID),
  UNIQUE INDEX AdminID_UNIQUE (AdminID ASC) VISIBLE);
  
/*-------------------------------------------------------------------------------------*/
  
INSERT INTO HomeServicesDB.tblAdmin (AdminID, FirstName, LastName, StreetAddress1, StreetAddress2, City, Country, MobilePhone, Email, Password, AccountType, Active)

VALUES
	(null, "Avinash", "Bissoon", "#25 Honey Bee Drive", "Bye Pass Road", "Arima", "Trinidad", "(868)723-0141", "avi@gmail.com", "Pass", "Administrator", 1),
    (null, "Nicholas", "Brathwaite", "#17 Orange Flats", "Cherry Circular", "Point Fortin", "Trinidad", "(868)229-4091", "nicholas@gmail.com", "Pass", "Administrator", 1),
    (null, "Kerry", "Maze", "#61 Montest Drive", "Glen Road", "Scarborough", "Tobago", "(868)345-0987", "kerry@gmail.com", "Pass", "Administrator", 1),
;

/*-------------------------------------------------------------------------------------*/
-- Create tblUsers
CREATE TABLE HomeServicesDB.tblUsers (
UserID INT NOT NULL AUTO_INCREMENT,
FirstName VARCHAR(45) NOT NULL,
LastName VARCHAR(45) NOT NULL,
MobilePhone VARCHAR(45) NULL DEFAULT NULL,
Email VARCHAR(45) NOT NULL,
Password VARCHAR(45) NOT NULL,
AccountType VARCHAR(45) NOT NULL,
PRIMARY KEY (UserID),
UNIQUE INDEX Email_UNIQUE (Email ASC)
);


-- Sync Customer Information to tblUsers
INSERT INTO HomeServicesDB.tblUsers (FirstName, LastName, MobilePhone, Email, Password, AccountType)
SELECT FirstName, LastName, MobilePhone, Email, Password, 'Customer'
FROM HomeServicesDB.tblCustomer;

-- Sync Employee Information to tblUsers
INSERT INTO HomeServicesDB.tblUsers (FirstName, LastName, MobilePhone, Email, Password, AccountType)
SELECT FirstName, LastName, MobilePhone, Email, Password, 'Employee'
FROM HomeServicesDB.tblEmployee;

-- Sync Admin Information to tblUsers
INSERT INTO HomeServicesDB.tblUsers (FirstName, LastName, MobilePhone, Email, Password, AccountType)
SELECT FirstName, LastName, MobilePhone, Email, Password, 'Admin'
FROM HomeServicesDB.tblAdmin;


/*-------------------------------------------------------------------------------------*/
CREATE TABLE HomeServicesDB.tblBooking (
BookingID INT NOT NULL AUTO_INCREMENT,
UserID INT NOT NULL,
ServiceType VARCHAR(45) NOT NULL,
ServiceDetails VARCHAR (255) NOT NULL,
Address VARCHAR(45) NOT NULL,
City VARCHAR(45) NOT NULL,
BookingDate DATE NOT NULL,
BookingTime TIME NOT NULL,
Status varchar(45) DEFAULT "Pending",
AssignedTo varchar(50) DEFAULT "Awaiting Professional",
PRIMARY KEY (BookingID),
FOREIGN KEY (UserID) REFERENCES HomeServicesDB.tblUsers(UserID)
);

INSERT INTO homeservicesdb.tblBooking (UserID, ServiceType, ServiceDetails, Address, City, BookingDate, BookingTime, Status, AssignedTo)
VALUES
(2, 'Electrical: Outlet and Switch Repair', 'Kitchen lights do not come on when the switch is turned on', '#45 Park Palace', 'Port of Spain', '2026-04-16', '9:00:00', 'Pending', 'Awaiting Professional'),
(4, 'Plumbing: Pipeline Repair', 'Bathroom sink is leaking heavily', '#24 Orchard Drive', 'Trincity', '2026-04-22', '12:00:00', 'Completed', 'Roshan Babwah'),
(5, 'Landscaping: Lawn Maintenance', 'Front lawn needs mowing and cleaning', '#105 Gageda Road', 'Sangre Grande', '2026-04-11', '2:00:00', 'Pending', 'Awaiting Professional'),
(3, 'Landscaping: Tree Trimming', 'My flower garden hedges needs trimming', '#Osprey Drive', 'San Fernando', '2026-04-28', '5:00:00', 'Pending', 'Awaiting Professional'),
(1, 'Housekeeping: Post Construction Cleaning', 'My home has just been built and I need to clean before moving in', '#28 Helios street', 'Couva', '2026-04-17', '01:00:00', 'Pending', 'Awaiting Professional')
;