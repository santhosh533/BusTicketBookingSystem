package btbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BusTicketBookingSystem {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bus_ticket_booking";
    private static final String USER = "root";
    private static final String PASS = "@Santhosh420";
    private static Scanner scanner = new Scanner(System.in);
    private static Connection conn;

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to Bus Ticket Booking Database!");

            while (true) {
                System.out.println("\n1. Admin Login\n2. User Register\n3. User Login\n4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        adminLogin();
                        break;
                    case 2:
                        registerUser();
                        break;
                    case 3:
                        userLogin();
                        break;
                    case 4:
                        System.out.println("Exiting system...");
                        conn.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void adminLogin() throws SQLException {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        String query = "SELECT id FROM admins WHERE username=? AND password=?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            System.out.println("Admin Login Successful!");
        } else {
            System.out.println("Invalid Admin Credentials.");
        }
    }

    private static void registerUser() throws SQLException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, password);

        int rows = pstmt.executeUpdate();
        System.out.println(rows > 0 ? "Registration successful!" : "Registration failed!");
    }

    private static void userLogin() throws SQLException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String query = "SELECT id FROM users WHERE username=? AND password=?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            int userId = rs.getInt("id");
            System.out.println("Login Successful! Welcome, " + username);
            userMenu(userId);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void userMenu(int userId) throws SQLException {
        while (true) {
            System.out.println("\n1. View Available Buses\n2. Book Ticket\n3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableBuses();
                    break;
                case 2:
                    bookTicket(userId);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void viewAvailableBuses() throws SQLException {
        String query = "SELECT * FROM buses WHERE available_seats > 0";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\nAvailable Buses:");
        while (rs.next()) {
            System.out.println("Bus ID: " + rs.getInt("id") + ", Name: " + rs.getString("bus_name") + ", From: "
                    + rs.getString("source") + " To: " + rs.getString("destination") + ", Seats Available: "
                    + rs.getInt("available_seats") + ", Fare: " + rs.getDouble("fare"));
        }
    }

    private static void bookTicket(int userId) throws SQLException {
        System.out.print("Enter Bus ID to book: ");
        int busId = scanner.nextInt();
        System.out.print("Enter number of seats to book: ");
        int seats = scanner.nextInt();

        String fareQuery = "SELECT fare FROM buses WHERE id = ?";
        PreparedStatement fareStmt = conn.prepareStatement(fareQuery);
        fareStmt.setInt(1, busId);
        ResultSet fareRs = fareStmt.executeQuery();

        if (fareRs.next()) {
            double farePerSeat = fareRs.getDouble("fare");
            double totalCost = farePerSeat * seats;

            String query = "UPDATE buses SET available_seats = available_seats - ? WHERE id = ? AND available_seats >= ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, seats);
            pstmt.setInt(2, busId);
            pstmt.setInt(3, seats);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Booking successful! Total cost: " + totalCost);
            } else {
                System.out.println("Booking failed! Not enough seats.");
            }
        } else {
            System.out.println("Invalid Bus ID.");
        }
    }

}
