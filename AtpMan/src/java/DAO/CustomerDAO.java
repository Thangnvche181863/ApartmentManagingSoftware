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
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.UtilHashPass;

public class CustomerDAO {
    
    private static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
    
    public boolean checkUsername(String username) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM customer WHERE username = ?";
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
            String sql = "SELECT password FROM customer WHERE username = ?";
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


    
    public boolean existsByUsernameOrGmail(String username, String gmail) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM customer WHERE username = ? OR gmail = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, gmail);
                    try (ResultSet rs = ps.executeQuery()) {
                        return rs.next();
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error checking existence by username or gmail", e);
        }
        return false;
    }
    public void createNewCustomer(String username, String email, String phone_number, String password) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
//                String hashedPassword = password != null ? UtilHashPass.EncodePassword(password) : null; // Encode password if not null
                String sql = "INSERT INTO customer (username, password, phone_number, gmail) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
//                    ps.setString(2, hashedPassword); // Save hashed password, could be null
                    ps.setString(2, password);
                    ps.setString(3, phone_number);
                    ps.setString(4, email);
                    
                    ps.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error creating new customer", e);
        } finally {
            DBContext.closeConnection(conn);
        }
    }
//    public Customer getCustomerByUsername(String username) {
//        Connection conn = null;
//        Customer customer = null;
//        try {
//            conn = DBContext.getConnection();
//            if (conn != null) {
//                String sql = "SELECT * FROM customer WHERE username = ?";
//                try (PreparedStatement statement = conn.prepareStatement(sql)) {
//                    statement.setString(1, username);
//                    try (ResultSet resultSet = statement.executeQuery()) {
//                        if (resultSet.next()) {
//                            customer = getCustomer(resultSet.getInt("customer_id"));
//                        }
//                    }
//                }
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            LOGGER.log(Level.SEVERE, "Error getting customer by username", e);
//        } finally {
//            DBContext.closeConnection(conn);
//        }
//        return customer;
//    }
}
