/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Staff;
import utils.DBContext;
import utils.UtilHashPass;

/**
 *
 * @author WuanTun
 */
public class StaffDAO {

    private static final Logger LOGGER = Logger.getLogger(StaffDAO.class.getName());

    public boolean checkUsername(String username) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Staff WHERE username = ?";
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
                String sql = "SELECT password FROM Staff WHERE username = ?";
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
                String sql = "SELECT password FROM Staff WHERE username = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String storedPassword = rs.getString("password");

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

    public Staff getAllInformationstaff(String username, String password) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Staff WHERE username = ? and password = ?";
                String hashedInputPassword = UtilHashPass.EncodePassword(password);
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, hashedInputPassword);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            Staff staff = new Staff();
                            staff.setStaffID(rs.getInt("staffID"));
                            staff.setUsername(rs.getString("username"));
                            staff.setName(rs.getString("name"));
                            staff.setEmail(rs.getString("email"));
                            staff.setPhoneNumber(rs.getString("phoneNumber"));
                            staff.setHireDate(rs.getDate("hireDate"));
                            return staff;
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
                String sql = "SELECT * FROM Staff WHERE username = ? OR email = ?";
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

    public void createNewStaff(int roleID, String username, String password, String name, String email, String phoneNumber, Date hireDate, int status) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String hashedInputPassword = UtilHashPass.EncodePassword(password);
                String sql = "INSERT INTO Staff (roleID, username, password, name, email, phoneNumber, hireDate, status) VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, roleID);
                    ps.setString(2, username);
                    ps.setString(3, hashedInputPassword);
                    ps.setString(4, name);
                    ps.setString(5, email);
                    ps.setString(6, phoneNumber);
                    ps.setDate(7, new java.sql.Date(hireDate.getTime()));
//                    ps.setInt(8, status); 
                    ps.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error creating new staff", e);
        } finally {
            DBContext.closeConnection(conn);
        }
    }

    public int getStaffIDByUsername(String username) {
        Connection conn = null;
        int staffID = -1;
        try {
            conn = DBContext.getConnection();
            String sql = "SELECT staffID FROM Staff WHERE username = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        staffID = rs.getInt("staffID");
                        System.out.println("Retrieved staffID: " + staffID);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error getting staffID", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return staffID;
    }

    // QUAN
    public boolean updatePassword(int staffID, String newPassword) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "UPDATE Staff SET password = ? WHERE staffID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    // In ra giá trị staffID để kiểm tra
                    System.out.println("Updating password for Staff ID: " + staffID);

                    String hashedPassword = UtilHashPass.EncodePassword(newPassword);
                    ps.setString(1, hashedPassword);
                    ps.setInt(2, staffID);

                    int rowsUpdated = ps.executeUpdate();
                    System.out.println("Rows Updated: " + rowsUpdated);

                    return rowsUpdated > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error updating password", ex);
        } finally {
            DBContext.closeConnection(conn);
        }
        return false;
    }

}
