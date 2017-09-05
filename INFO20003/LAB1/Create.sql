CREATE TABLE Customer(
	CustomerID SMALLINT AUTO_INCREMENT,
    CustomerFirstName VARCHAR(50),
    CustomerLastName VARCHAR(50) NOT NULL,
    CustomerPhone VARCHAR(16) NOT NULL,
    CustomerBirthDate Date NOT NULL,
    PRIMARY KEY (CustomerID)
);

/* this create a  table inside the school server*/