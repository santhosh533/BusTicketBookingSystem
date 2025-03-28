-- Create Database
CREATE DATABASE bus_ticket_booking;
USE bus_ticket_booking;

-- Create Admin Table
CREATE TABLE admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Insert Default Admin
INSERT INTO admins (username, password) VALUES ('guru', 'guru123');

-- Create Users Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Create Buses Table
CREATE TABLE buses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bus_name VARCHAR(100) NOT NULL,
    is_ac BOOLEAN NOT NULL,
    type ENUM('Seater', 'Sleeper') NOT NULL,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    total_seats INT NOT NULL,
    available_seats INT NOT NULL,
    fare DECIMAL(10,2) NOT NULL
);

-- Create Bookings Table
CREATE TABLE bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    bus_id INT NOT NULL,
    seats_booked INT NOT NULL,
    total_cost DECIMAL(10,2) NOT NULL,
    booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (bus_id) REFERENCES buses(id) ON DELETE CASCADE
);

-- Insert Sample Buses
INSERT INTO buses (bus_name, is_ac, type, source, destination, total_seats, available_seats, fare) 
VALUES 
('SRT 1', TRUE, 'Seater', 'Namakkal', 'Bangalore', 40, 40, 500.00),
('SRT 2', FALSE, 'Sleeper', 'Namakkal', 'Chennai', 30, 30, 750.00);
