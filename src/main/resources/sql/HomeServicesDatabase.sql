/*-------------------------------------------------------------------------------------*/
DROP DATABASE homeservicesdb;
CREATE DATABASE homeservicesdb;
USE homeservicesdb;

/*-------------------------------------------------------------------------------------*/
DROP SCHEMA homeservicesdb;
CREATE SCHEMA homeservicesdb;
USE homeservicesdb;

/*-------------------------------------------------------------------------------------*/
--Create tblcustomer

CREATE TABLE homeservicesdb.tblcustomer (
  customerid INT NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(45) NOT NULL,
  lastname VARCHAR(45) NOT NULL,
  streetaddress1 VARCHAR(45) NOT NULL,
  streetaddress2 VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  country VARCHAR(45) NOT NULL,
  mobilephone VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  accounttype VARCHAR(45) NOT NULL,
  active TINYINT NOT NULL,
  PRIMARY KEY (customerid),
  UNIQUE INDEX customerid_UNIQUE (customerid ASC) VISIBLE);

/*-------------------------------------------------------------------------------------*/
INSERT INTO homeservicesdb.tblcustomer (customerid, firstname, lastname, streetaddress1, streetaddress2, city, country, mobilephone, email, password, accounttype, active)

VALUES
	(null, "Aaron", "Kalloo", "#7 Valley View Drive", "Pinto Road", "Arima", "Trinidad", "(868)717-8916", "aaronkalloo@gmail.com", "Pass", "Customer", 1),
    (null, "Darion", "Thomas", "#22 Orange Drive", "Santa Rosa", "Arima", "Trinidad", "(868)284-0091", "darionthomas@gmail.com", "Pass", "Customer", 1),
    (null, "Jonathan", "Bachan", "#55 Cresend Drive", "Long Circular Road", "San Fernando", "Trinidad", "(868)324-6213", "johnny@gmail.com", "Pass", "Customer", 1),
    (null, "Kimmy", "Khan", "#4 Bon Accord Drive", "Simple Road", "Scarborough", "Tobago", "(868)625-8821", "kimmykhan@gmail.com", "Pass", "Customer", 1),
    (null, "Selena", "Brown", "#107 Yellow Bird Drive", "Timberland Park", "Sangre Grande", "Trinidad", "(868)453-1298", "selena@gmail.com", "Pass", "Customer", 1),
;

/*-------------------------------------------------------------------------------------*/
  --Create tblemployee

CREATE TABLE homeservicesdb.tblemployee (
employeeid INT NOT NULL AUTO_INCREMENT,
firstname VARCHAR(45) NOT NULL,
lastname VARCHAR(45) NOT NULL,
streetaddress1 VARCHAR(45) NOT NULL,
streetaddress2 VARCHAR(45) NOT NULL,
city VARCHAR(45) NOT NULL,
country VARCHAR(45) NOT NULL,
mobilephone VARCHAR(45) NOT NULL,
email VARCHAR(45) NOT NULL,
password VARCHAR(45) NOT NULL,
accounttype VARCHAR(45) NOT NULL,
active TINYINT NOT NULL,
PRIMARY KEY (employeeid),
UNIQUE INDEX employeeid_UNIQUE (employeeid ASC) VISIBLE);

/*-------------------------------------------------------------------------------------*/
INSERT INTO homeservicesdb.tblemployee (employeeid, firstname, lastname, streetaddress1, streetaddress2, city, country, mobilephone, email, password, accounttype, active)

VALUES
	(null, "Matthew", "Chandoo", "#18 Point Road", "Hich Estate", "Chagunas", "Trinidad", "(868)668-0198", "matty@gmail.com", "Pass", "Employee", 1),
    (null, "Renaldo", "Boodhoo", "#321 Jump Street", "Dememara Road", "Arima", "Trinidad", "(868)342-0012", "renaldo@gmail.com", "Pass", "Employee", 1),
    (null, "Amelia", "Terry", "#1 Kenny Drive", "Gold Street", "Bon Accord", "Tobago", "(868)782-8723", "amelia@gmail.com", "Pass", "Employee", 1),
    (null, "Roshan", "Babwah", "#34 Love Road", "Cupid Park", "Couva", "Trinidad", "(868)224-0819", "roshan@gmail.com", "Pass", "Employee", 1),
    (null, "Maile", "Harry", "#76 Red Road", "The Plantations", "Scarborough", "Tobago", "(868)771-0673", "malie@gmail.com", "Pass", "Employee", 1),
;

/*-------------------------------------------------------------------------------------*/
 --Create tbladmin

  CREATE TABLE homeservicesdb.tbladmin (
  adminid INT NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(45) NOT NULL,
  lastname VARCHAR(45) NOT NULL,
  streetaddress1 VARCHAR(45) NOT NULL,
  streetaddress2 VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  country VARCHAR(45) NOT NULL,
  mobilephone VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  accounttype VARCHAR(45) NOT NULL,
  active TINYINT NOT NULL,
  PRIMARY KEY (adminid),
  UNIQUE INDEX adminid_UNIQUE (adminid ASC) VISIBLE);
  
/*-------------------------------------------------------------------------------------*/
  
INSERT INTO homeservicesdb.tbladmin (adminid, firstname, lastname, streetaddress1, streetaddress2, city, country, mobilephone, email, password, accounttype, active)

VALUES
	(null, "Avinash", "Bissoon", "#25 Honey Bee Drive", "Bye Pass Road", "Arima", "Trinidad", "(868)723-0141", "avi@gmail.com", "Pass", "Administrator", 1),
    (null, "Nicholas", "Brathwaite", "#17 Orange Flats", "Cherry Circular", "Point Fortin", "Trinidad", "(868)229-4091", "nicholas@gmail.com", "Pass", "Administrator", 1),
    (null, "Kerry", "Maze", "#61 Montest Drive", "Glen Road", "Scarborough", "Tobago", "(868)345-0987", "kerry@gmail.com", "Pass", "Administrator", 1),
;

/*-------------------------------------------------------------------------------------*/
-- Create tblusers
CREATE TABLE homeservicesdb.tblusers (
userid INT NOT NULL AUTO_INCREMENT,
firstname VARCHAR(45) NOT NULL,
lastname VARCHAR(45) NOT NULL,
mobilephone VARCHAR(45) NULL DEFAULT NULL,
email VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
accounttype VARCHAR(50) NOT NULL,
PRIMARY KEY (userid),
UNIQUE INDEX email_UNIQUE (email ASC)
);


-- Sync Customer Information to tblusers
INSERT INTO homeservicesdb.tblusers (firstname, lastname, mobilephone, email, password, accounttype)
SELECT firstname, lastname, mobilephone, email, password, 'Customer'
FROM homeservicesdb.tblcustomer;

-- Sync Employee Information to tblusers
INSERT INTO homeservicesdb.tblusers (firstname, lastname, mobilephone, email, password, accounttype)
SELECT firstname, lastname, mobilephone, email, password, 'Employee'
FROM homeservicesdb.tblemployee;

-- Sync Admin Information to tblusers
INSERT INTO homeservicesdb.tblusers (firstname, lastname, mobilephone, email, password, accounttype)
SELECT firstname, lastname, mobilephone, email, password, 'Admin'
FROM homeservicesdb.tbladmin;


/*-------------------------------------------------------------------------------------*/
CREATE TABLE homeservicesdb.tblBooking (
bookingid INT NOT NULL AUTO_INCREMENT,
userid INT NOT NULL,
servicetype VARCHAR(45) NOT NULL,
servicedetails VARCHAR (45) NOT NULL,
address VARCHAR(45) NOT NULL,
city VARCHAR(45) NOT NULL,
booking_date DATE NOT NULL,
booking_time TIME NOT NULL,
status varchar(255) DEFAULT "Pending",
assigned_to varchar(255) DEFAULT "Awaiting Professional",
PRIMARY KEY (bookingid),
FOREIGN KEY (userid) REFERENCES javafxproject.tblusers(userid)
);