CREATE TABLE `users` (
`username` varchar(16) NOT NULL,
`password` varchar(32) NOT NULL,
`email` varchar(255) DEFAULT NULL,
`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

DROP TABLE IF EXISTS PRODUCTS;
CREATE TABLE PRODUCTS (
	ID VARCHAR(25) PRIMARY KEY,
	NAME VARCHAR(50),
	DESCRIPTION VARCHAR(250),
	UNIT_PRICE DECIMAL,
	MANUFACTURER VARCHAR(50),
	CATEGORY VARCHAR(50),
	CONDITION VARCHAR(50),
	UNITS_IN_STOCK BIGINT,
	UNITS_IN_ORDER BIGINT,
	DISCONTINUED BOOLEAN
);

DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
	ID VARCHAR(25) PRIMARY KEY,
	NAME VARCHAR(50),
	address  VARCHAR(250),
	noOfOrdersMade INT
);

