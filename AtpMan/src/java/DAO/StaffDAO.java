/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
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

    public List<Staff> getAllStaff() {
        List<Staff> list = new ArrayList<>();
        Connection conn = null;
        String sql = "select * from Staff";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int staffID = rs.getInt(1);
                int roleID = rs.getInt(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(6);
                String phoneNumber = rs.getString(7);
                String name = rs.getString(5);
                Date hireDate = rs.getDate(8);
                Staff staff = new Staff(staffID, roleID, username, password, email, phoneNumber, name, hireDate);
                list.add(staff);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<String> getRoleNameByID() {

        String sql = "Select R.role_name from Role R join Staff S on s.roleID = r.roleID ";
        List<String> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {

                list.add(rs.getString(1));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int dismissStaff(int staffID) {
        int n = 0;
        String sql = "DELETE FROM Staff\n"
                + "WHERE staffID = ?";
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, staffID);

            n = pre.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return n;

    }

    public int getAmountOfStaff() {
        StaffDAO dao = new StaffDAO();
        List<Staff> list = dao.getAllStaff();
        int amountStaff = list.size();
        return amountStaff;
    }

    public int UpdateStaffInfo(String name, String phoneNumber, int staffID) {
        int n = 0;
        String sql = "UPDATE Staff SET name = ?, phoneNumber = ? WHERE staffID = ?;";

        // Sử dụng try-with-resources để đảm bảo kết nối và preparedStatement được đóng tự động
        try (Connection conn = DBContext.getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {

            // Thiết lập các giá trị cho câu lệnh SQL
            pre.setString(1, name);
            pre.setString(2, phoneNumber);
            pre.setInt(3, staffID);

            // Thực thi câu lệnh SQL và trả về số dòng được cập nhật
            n = pre.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return n; // Trả về số dòng được cập nhật (0 nếu không có dòng nào)
    }

    public Staff getStaffByID(int staffID) {
        Staff staff = new Staff();
        Connection conn = null;
        String sql = "select * from Staff where staffID = ?";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, staffID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {

                int roleID = rs.getInt(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(5);
                String phoneNumber = rs.getString(6);
                String name = rs.getString(7);
                Date hireDate = rs.getDate(8);
                staff = new Staff(staffID, roleID, username, password, email, phoneNumber, name, hireDate);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return staff;
    }

    public List<Staff> staffPaging(int page, int recordsPerPage) {
        List<Staff> list = new ArrayList<>();
        Connection conn = null;
        try {
            int offset = (page - 1) * recordsPerPage;
            String sql = "select * from Staff order by staffID offset ? rows fetch next ? rows only";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, offset);
            ps.setInt(2, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff();
                int staffID = rs.getInt(1);
                int roleID = rs.getInt(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(5);
                String phoneNumber = rs.getString(6);
                String name = rs.getString(7);
                java.util.Date hireDate = rs.getDate(8);
                list.add(staff);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {

        StaffDAO dao = new StaffDAO();
        int n = dao.UpdateStaffInfo("Duy Anh5", "5555555555", 4);
        System.out.println("Húp" + n);

    }
}
