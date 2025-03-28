# Bus Ticket Booking System

## Overview
The **Bus Ticket Booking System** is a Java-based console application that allows users to register, log in, and book bus tickets. Admins can manage the system, while users can view available buses and make bookings. The system uses **MySQL** for data storage and **JDBC** for database connectivity.

## Features
- **Admin Login**: Authenticate as an admin to manage the system.
- **User Registration & Login**: New users can sign up and log in.
- **View Available Buses**: Users can see available buses with details.
- **Book Tickets**: Users can book tickets for a selected bus.
- **Database Integration**: Data is stored securely in MySQL.

## Technologies Used
- **Java** (JDK 8+)
- **MySQL** (Database Management System)
- **JDBC** (Java Database Connectivity)
- **Eclipse/IntelliJ** (Recommended IDEs)

## Database Schema
### Tables:
1. **admins**: Stores admin credentials.
2. **users**: Stores registered users.
3. **buses**: Contains bus details.
4. **bookings**: Records user bookings.

## Setup Instructions
### Prerequisites:
- Install [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- Install [MySQL](https://dev.mysql.com/downloads/)
- Set up a MySQL database and user.

### Steps:
1. Clone the repository:
   ```sh
   git clone https://github.com/santhosh533/BusTicketBookingSystem.git
   ```
2. Open MySQL and execute the provided SQL script (`database.sql`) to create the database.
3. Update the `DB_URL`, `USER`, and `PASS` in `BusTicketBookingSystem.java`.
4. Compile and run the Java program:
   ```sh
   javac BusTicketBookingSystem.java
   java BusTicketBookingSystem
   ```

## Usage
1. **Run the application**
2. Choose an option from the main menu:
   - Admin Login
   - User Register
   - User Login
3. **Admin** can log in and manage bus schedules.
4. **Users** can view buses and book tickets.

## Sample Admin Credentials
```
Username: guru
Password: guru123
```

## Future Enhancements
- Implement GUI for better user experience.
- Add payment gateway integration.
- Improve security with password encryption.

## License
This project is open-source and available under the [MIT License](LICENSE).

