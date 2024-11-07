/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author WuanTun
 */
import utils.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import utils.UtilHashPass;

public class CustomerDAO {

    private static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());

    // QUAN
    public boolean checkUsername(String username) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Customer WHERE username = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    try (ResultSet rs = ps.executeQuery()) {
                        return rs.next(); // If a record is found, return true
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error checking username", ex);
        }
        return false; // Return false if connection is null or if an exception occurs
    }

    // QUAN
    public boolean checkPassword(String username, String password) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT password FROM Customer WHERE username = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String storedPassword = rs.getString("password");
                            // Mã hóa mật khẩu người dùng nhập trước khi so sánh
                            String hashedInputPassword = UtilHashPass.EncodePassword(password);

                            // So sánh mật khẩu đã mã hóa
                            return storedPassword.equals(hashedInputPassword);
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error checking password", e);
        }
        return false;
    }

    // QUAN
    public boolean checkAuthenticationUser(String username, String password) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT password FROM Customer WHERE username = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String storedPassword = rs.getString("password"); // da ma hoa

                            String hashedInputPassword = UtilHashPass.EncodePassword(password);

                            return storedPassword.equals(hashedInputPassword);
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error during authentication", e);
        }
        return false;
    }

    // QUAN
    public Customer getAllInformationCustomer(String username, String password) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String hashedInputPassword = UtilHashPass.EncodePassword(password);
                String sql = "SELECT * FROM Customer WHERE username = ? and password = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, hashedInputPassword);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            Customer customer = new Customer();
                            customer.setCustomerID(rs.getInt("customerID")); // Lấy customerID từ kết quả
                            customer.setUsername(rs.getString("username"));
                            customer.setName(rs.getString("name"));
                            customer.setEmail(rs.getString("email"));
                            customer.setPhoneNumber(rs.getString("phoneNumber"));
                            customer.setDob(rs.getDate("dob"));
                            customer.setRegistrationDate(rs.getDate("registrationDate"));
                            customer.setIsOwner(rs.getInt("isOwner"));
                            customer.setStatus(rs.getInt("status"));
                            return customer;
                        }
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, "Error retrieving customer information", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return null; // Trả về null nếu không tìm thấy hoặc có lỗi xảy ra
    }

    public String getEmailByCustomerID(int customerID) throws SQLException {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            String sql = "SELECT email FROM Customer WHERE customerID = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, customerID);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("email");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error finding email by customerID", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return null; // Trả về null nếu không tìm thấy email
    }

    // QUAN
    public boolean existsByUsernameOrGmail(String username, String email) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Customer WHERE username = ? OR email = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, email);
                    try (ResultSet rs = ps.executeQuery()) {
                        return rs.next();
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error checking existence by username or email", e);
        }
        return false;
    }

    // QUAN
    public void createNewCustomer(String username, String password, String name, String email, String phoneNumber,
            String isOwner) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String hashedInputPassword = UtilHashPass.EncodePassword(password);
                String sql = "INSERT INTO Customer (username, password, name, email, phoneNumber, isOwner) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, hashedInputPassword); // Save plain password, or hash it if needed
                    ps.setString(3, name);
                    ps.setString(4, email);
                    ps.setString(5, phoneNumber);
                    ps.setString(6, isOwner); // 1 for Resident, 0 for Owner
                    ps.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error creating new customer", e);
        } finally {
            DBContext.closeConnection(conn);
        }
    }

    // QUAN
    public int getCustomerIDByUsername(String username) {
        Connection conn = null;
        int customerID = -1;
        try {
            conn = DBContext.getConnection();
            String sql = "SELECT customerID FROM Customer WHERE username = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        customerID = rs.getInt("customerID");
                        System.out.println("Retrieved customerID: " + customerID);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error getting customerID", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return customerID;
    }

    // QUAN
    public String getPasswordByID(int customerID) {
        Connection conn = null;
        String password = null;
        try {
            conn = DBContext.getConnection();
            String sql = "SELECT password FROM Customer WHERE customerID = ? ";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, customerID);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        password = rs.getString("password");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error finding password by ID", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return password;
    }

    // QUAN
    public boolean updatePassword(int customerID, String newPassword) {

        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "UPDATE Customer SET password = ? WHERE customerID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    String hashedPassword = UtilHashPass.EncodePassword(newPassword); // Hash the new password
                    ps.setString(1, hashedPassword);
                    ps.setInt(2, customerID);
                    int rowsUpdated = ps.executeUpdate();
                    System.out.println("Rows Updated: " + rowsUpdated);

                    return rowsUpdated > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error updating password", ex);
        } finally {
            DBContext.closeConnection(conn); // Ensure connection is closed
        }
        return false; // Return false if connection is null or if an exception occurs
    }

    // QUAN
    public boolean updateCustomerEmail(int customerID, String newemail) throws SQLException {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "UPDATE Customer SET email = ? WHERE customerID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, newemail);
                    ps.setInt(2, customerID);
                    int rowsUpdate = ps.executeUpdate();
                    System.out.println("Rows update: " + rowsUpdate);
                    return rowsUpdate > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error updating password", ex);
        } finally {
            DBContext.closeConnection(conn);
        }
        return false;
    }

    // QUAN
    public Customer findCustomerByGmail(String gmail) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT customerID FROM Customer WHERE email = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, gmail);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            return getCustomer(rs.getInt("customerID"));
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error finding customer by gmail", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return null;
    }

    // QUAN
    public Customer getCustomer(int id) {
        Connection conn = null;
        Customer customer = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Customer WHERE customerID = ?";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setInt(1, id);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            customer = new Customer(id,
                                    resultSet.getString("username"),
                                    resultSet.getString("name"),
                                    resultSet.getString("email"),
                                    resultSet.getString("phoneNumber"),
                                    resultSet.getDate("dob"),
                                    resultSet.getDate("registrationDate"),
                                    resultSet.getInt("isOwner"),
                                    resultSet.getInt("status"));
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error getting customer", e);
        }
        return customer;
    }

    // KhangPM
    public List<Customer> getLivingInApartment(int apartmentID) {
        List<Customer> list = new ArrayList<>();
        Connection connection = null;
        String sql = "select c.customerID, c.name, c.email, c.phoneNumber, c.dob, c.isOwner from Customer c\n"
                + "inner join Living l on l.customerID = c.customerID\n"
                + "where l.apartmentID = ? "
                + "and endDate is null";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setEmail(rs.getString(3));
                customer.setPhoneNumber(rs.getString(4));
                customer.setDob(rs.getDate("dob"));
                customer.setIsOwner(rs.getInt(6));
                list.add(customer);
            }
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return list;
    }

    public boolean addCustomerToApartment(Customer customer, int apartmentID, Date startDate) {
        String customerSql = "INSERT INTO Customer (name, email, phoneNumber, dob, registrationDate, isOwner, status) VALUES (?, ?, ?, ?, ?, 0, 3)";
        String livingSql = "INSERT INTO Living (customerID, apartmentID, startDate) VALUES (?, ?, ?)";
        boolean isAdded = false;
        Connection conn = null;

        try {
            conn = DBContext.getConnection();
            if (conn == null || conn.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return false;
            }

            // Disable auto-commit for transaction management
            conn.setAutoCommit(false);

            // Insert customer into Customer table
            try (PreparedStatement customerStmt = conn.prepareStatement(customerSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                customerStmt.setString(1, customer.getName());
                customerStmt.setString(2, customer.getEmail());
                customerStmt.setString(3, customer.getPhoneNumber());
                customerStmt.setDate(4, customer.getDob() != null ? new java.sql.Date(customer.getDob().getTime()) : null);
                customerStmt.setDate(5, customer.getRegistrationDate() != null ? new java.sql.Date(customer.getRegistrationDate().getTime()) : null);
               

                int customerRows = customerStmt.executeUpdate();
                if (customerRows > 0) {
                    try (ResultSet generatedKeys = customerStmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int newCustomerID = generatedKeys.getInt(1);
                            customer.setCustomerID(newCustomerID);
                            LOGGER.log(Level.INFO, "Added Customer with ID: {0}", newCustomerID);

                            // Insert record into Living table
                            try (PreparedStatement livingStmt = conn.prepareStatement(livingSql)) {
                                livingStmt.setInt(1, newCustomerID);
                                livingStmt.setInt(2, apartmentID);
                                livingStmt.setDate(3, startDate != null ? new java.sql.Date(startDate.getTime()) : null);

                                int livingRows = livingStmt.executeUpdate();
                                if (livingRows > 0) {
                                    LOGGER.log(Level.INFO, "Living record added for Customer ID: {0}", newCustomerID);
                                    isAdded = true;
                                } else {
                                    throw new SQLException("Adding Living record failed.");
                                }
                            }
                        } else {
                            throw new SQLException("Adding customer failed, no ID obtained.");
                        }
                    }
                } else {
                    LOGGER.log(Level.WARNING, "No customer was added.");
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error adding customer and living record.", e);
        } finally {
            DBContext.closeConnection(conn);
        }

        return isAdded;
    }

    public static void main(String[] args) {
        Customer testCustomer = new Customer();
        testCustomer.setName("tester");
        testCustomer.setEmail("asdasd");
        testCustomer.setPhoneNumber("1234567890");
        testCustomer.setDob(java.sql.Date.valueOf("1990-01-01"));
        testCustomer.setRegistrationDate(new java.util.Date());
       

        int apartmentID = 1;  // Replace with a valid apartment ID from your database
        java.sql.Date startDate = java.sql.Date.valueOf("2023-01-01");
        

        // Create an instance of the class containing the addCustomerToApartment method
        CustomerDAO customerDAO = new CustomerDAO();  // Assuming the method is in CustomerDAO

        // Test addCustomerToApartment
        boolean isAdded = customerDAO.addCustomerToApartment(testCustomer, apartmentID, startDate);

        // Output the result
        if (isAdded) {
            System.out.println("Test Passed: Customer and living records added successfully.");
            System.out.println("Customer ID: " + testCustomer.getCustomerID());
        } else {
            System.out.println("Test Failed: Could not add customer and living records.");
        }

    }
}
