/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBContext;
import model.*;

/**
 *
 * @author ADMIN
 */
public class LivingDAO {

    public List<Living> getAllResident() {
        Connection connection = null;
        List<Living> list = new ArrayList<>();
        String sql = "select * from Living";
        try {
            connection = DBContext.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Living living = new Living();
                    living.setLivingID(rs.getInt(1));
                    living.setCustomerID(rs.getInt(2));
                    living.setApartmentID(rs.getInt(3));
                    living.setStartDate(rs.getDate(4));
                    living.setEndDate(rs.getDate(5));
                    list.add(living);
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {

        }
        return list;
    }

    public int getAmountOfResident() {
        LivingDAO dao = new LivingDAO();
        List<Living> list = dao.getAllResident();
        return list.size();
    }

    public List<String> getNameOfResident(int apartmentID) {
        List<String> list = new ArrayList<>();
        String sql = " SELECT c.name FROM Customer c\n"
                + "JOIN Living l ON c.customerID = l.customerID\n"
                + "JOIN Apartment a ON l.apartmentID = a.apartmentID\n"
                + "WHERE a.apartmentID = ?";
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, apartmentID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {

                list.add(rs.getString(1));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Date> getDobOfResident(int apartmentID) {
        List<Date> list = new ArrayList<>();
        String sql = "SELECT c.dob FROM Customer c\n"
                + "JOIN Living l ON c.customerID = l.customerID\n"
                + "JOIN Apartment a ON l.apartmentID = a.apartmentID\n"
                + "WHERE a.apartmentID = ?";
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, apartmentID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                list.add(rs.getDate(1)); // Lấy dữ liệu kiểu Date
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public List<Living> getAllResidentByApartmentID(int apartmentid) {
        List<Living> list = new ArrayList<>();
        Connection conn = null;
        String sql = "select * from Living where apartmentID = ?";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, apartmentid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int livingID = rs.getInt(1);
                int customerID = rs.getInt(2);
                int apartmentID = rs.getInt(3);
                Date startDate = rs.getDate(4),
                        endDate = rs.getDate(5);
                Living living = new Living(livingID, customerID, apartmentID, startDate, endDate);
                list.add(living);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<Integer> getAmountOfResidentOfApartment(int buildingID) {
        List<Integer> list = new ArrayList<>();
        String sql = " SELECT  A.apartmentID ,\n"
                + "COUNT(L.livingID) AS numberOfPeople FROM  Apartment A LEFT JOIN   Living L ON A.apartmentID = L.apartmentID where buildingID = ?\n"
                + "GROUP BY   A.apartmentID;";
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, buildingID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {

                list.add(rs.getInt(2));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    //////////////////////////////////////QUAN////////////////////////////////////
    private static final Logger LOGGER = Logger.getLogger(LivingDAO.class.getName());

    public void insertResident(int customerID, int apartmentID) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            String sql = "INSERT INTO Living (customerID, apartmentID) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, customerID);
                ps.setInt(2, apartmentID);
                ps.executeUpdate();
                System.out.println("Inserted into Living: customerID = " + customerID + ", apartmentID = " + apartmentID); // In thông báo thành công
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // In ra lỗi
            LOGGER.log(Level.SEVERE, "Error inserting into Living", e);
        } finally {
            DBContext.closeConnection(conn);
        }
    }

    public int insertLiving(int customerID, int apartmentID, LocalDate startDate) {
        String sql = "INSERT INTO Living (customerID, apartmentID, startDate) VALUES (?, ?, ?)";
        Connection conn = null;
        int isInserted = 0;

        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, customerID);
            pre.setInt(2, apartmentID);
            pre.setDate(3, java.sql.Date.valueOf(startDate)); // Chuyển LocalDate thành java.sql.Date

            isInserted = pre.executeUpdate();
            LOGGER.info("Dữ liệu đã được chèn thành công vào bảng Living.");
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error inserting data", ex);
            ex.printStackTrace();
        } finally {
            DBContext.closeConnection(conn);
        }
        return isInserted;
    }

    public static void main(String[] args) {
        LivingDAO dao = new LivingDAO();
        LocalDate startDate = LocalDate.now(); // Lấy ngày hiện tại

        int n = dao.insertLiving(17, 11, startDate);
        System.out.println("Kết quả: " + n);
    }
}
