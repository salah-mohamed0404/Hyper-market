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

INSERT INTO users VALUES (1, 'admin', 'admin', 'admin', 'admin')

GO

CREATE TABLE user_actions(
	id INT PRIMARY KEY IDENTITY(1, 1),
	[action] NVARCHAR(100) NOT NULL,
	createdAt DATE,
	userId INT REFERENCES users(id) ON DELETE CASCADE
)

GO

CREATE TABLE orders(
	id INT PRIMARY KEY IDENTITY(1, 1),
	createdAt DATE,
	isCanceled BIT DEFAULT(0) CHECK(isCanceled in (0, 1)),
	userId INT REFERENCES users(id) ON DELETE CASCADE
)

GO

CREATE TABLE products(
	id INT PRIMARY KEY IDENTITY(1, 1),
	[name] NVARCHAR(50) NOT NULL,
	price FLOAT NOT NULL,
	offerPrice FLOAT DEFAULT(-1),
	[expireDate] DATE NOT NULL,
	[type] nvarchar(100) NOT NULL,
	addedAt DATE,
	orderId INT REFERENCES orders(id) ON DELETE CASCADE
)