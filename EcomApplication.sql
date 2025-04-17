create database EcomApplication;

use EcomApplication;

create table Customer(
	customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(50),
    email VARCHAR(50),
    customer_password VARCHAR(50)
);

create table Products(
	product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    price DECIMAL (12,2),
    product_description VARCHAR(50),
    stock_quantity INT
);

create table Cart(
    cart_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY(customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY(product_id) REFERENCES  Products(product_id)
);

create table Orders(
	order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    order_date DATE,
    total_price DECIMAL(12,2),
    shipping_address VARCHAR(200),
    FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

create table Order_items(
	order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY(order_id) REFERENCES Orders(order_id),
    FOREIGN KEY(product_id) REFERENCES  Products(product_id)
);

select * from Customer;
select * from Products;
select * from Orders;
select * from Order_items;
select * from Cart;


