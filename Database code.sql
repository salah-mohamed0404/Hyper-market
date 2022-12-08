CREATE DATABASE HyperMarket

GO

USE HyperMarket

GO

CREATE TABLE users(
	id INT PRIMARY KEY,
	[name] NVARCHAR(50) NOT NULL,
	userName NVARCHAR(50) UNIQUE NOT NULL,
	[password] NVARCHAR(50) NOT NULL,
	[type] NVARCHAR(50) NOT NULL
)

INSERT INTO users VALUES (1, 'admin', 'admin1', 'admin', 'admin')

GO

CREATE TABLE user_actions(
	id INT PRIMARY KEY IDENTITY(1, 1),
	[action] NVARCHAR NOT NULL,
	createdAt DATE DEFAULT(GETDATE()),
	userId INT REFERENCES users(id) 
)

GO

CREATE TABLE products(
	id INT PRIMARY KEY,
	[name] NVARCHAR(50) NOT NULL,
	price FLOAT NOT NULL,
	offerPrice FLOAT DEFAULT(-1),
	isReturn BIT DEFAULT(0) CHECK(isReturn in (0, 1)),
	isDamage BIT DEFAULT(0) CHECK(isDamage in (0, 1)),
	[expireDate] DATE NOT NULL,
	addedAt DATE DEFAULT(GETDATE())
)

GO

CREATE TABLE orders(
	id INT PRIMARY KEY,
	createdAt DATE DEFAULT(GETDATE()),
	isCanceled BIT DEFAULT(0) CHECK(isCanceled in (0, 1)),
	userId INT REFERENCES users(id)
)

GO 

CREATE TABLE order_details(
	productId INT REFERENCES products(id),
	orderId INT REFERENCES orders(id),
	primary key(productId, orderId)
)