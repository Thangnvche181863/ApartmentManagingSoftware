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
                            String storedPassword = rs.getString("password"); //da ma hoa 

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
                            customer.setCustomerID(rs.getInt(1));
                            customer.setUsername(rs.getString("username"));
                            customer.setName(rs.getString("name"));
                            customer.setEmail(rs.getString("email"));
                            customer.setPhoneNumber(rs.getString("phoneNumber"));
                            customer.setAge(rs.getInt("age"));
                            customer.setRegistrationDate(rs.getDate("registrationDate"));
                            customer.setIsOwner(rs.getInt("isOwner"));
                            return customer;
                        }
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            DBContext.closeConnection(conn);
        }
        return null;
    }

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

    public void createNewCustomer(String username, String password, String name, String email, String phoneNumber, String age, String registrationDate, String isOwner) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String hashedInputPassword = UtilHashPass.EncodePassword(password);
                String sql = "INSERT INTO Customer (username, password, name, email, phoneNumber, age, registrationDate, isOwner) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, hashedInputPassword); // Save plain password, or hash it if needed
                    ps.setString(3, name);
                    ps.setString(4, email);
                    ps.setString(5, phoneNumber);
                    ps.setString(6, age);
                    ps.setString(7, registrationDate);
                    ps.setString(8, isOwner); // 1 for Resident, 0 for Owner
                    ps.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error creating new customer", e);
        } finally {
            DBContext.closeConnection(conn);
        }
    }

//    public static void main(String[] args) {
//        CustomerDAO dao = new CustomerDAO();
//        Customer c = dao.getAllInformationCustomer("nguyenquan", "sRY4rMY8/DtYD2+OQLAkTVClzcY=");
//        System.out.println(c.getName());
//    }
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
                                    resultSet.getInt("age"),
                                    resultSet.getDate("registrationDate"),
                                    resultSet.getInt("isOwner"));
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
        String sql = "select c.customerID, c.name, c.email, c.phoneNumber, c.age, c.isOwner, c.customerType from Customer c\n"
                + "inner join Living l on l.customerID = c.customerID\n"
                + "where l.apartmentID = ?";
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
                customer.setAge(rs.getInt(5));
                customer.setIsOwner(rs.getInt(6));
                list.add(customer);
            }
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return null;
    }

    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();
        List<Customer> list = dao.getLivingInApartment(1);
        for (Customer customer : list) {
            System.out.println(customer.getName());
        }
    }
}
