/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Apartment;
import model.Building;
import utils.DBContext;

/**
 *
 * @author Admin
 */
public class ApartmentDAO {

    public Vector<Apartment> getAllApartment() {
        Connection conn = null;
        Vector<Apartment> vector = new Vector<>();
        String sql = "select * from Apartment";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int apartmentID = rs.getInt(1);
                int buildingID = rs.getInt(2);
                String departmentType = rs.getString(3);
                double price = rs.getDouble(4);
                int floor = rs.getInt(5);
                int area = rs.getInt(6);
                Apartment apartment = new Apartment(apartmentID, buildingID, departmentType, price, floor, area);
                vector.add(apartment);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Apartment> getAllApartmentByID(int buildingID) {
        Connection conn = null;
        Vector<Apartment> vector = new Vector<>();
        String sql = "select * from Apartment where buildingID = ?";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, buildingID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int apartmentID = rs.getInt(1);
                String departmentType = rs.getString(3);
                double price = rs.getDouble(4);
                int floor = rs.getInt(5);
                int area = rs.getInt(6);
                Apartment apartment = new Apartment(apartmentID, buildingID, departmentType, price, floor, area);
                vector.add(apartment);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int getAmountOfApartment() {
        ApartmentDAO dao = new ApartmentDAO();
        Vector<Apartment> vector = dao.getAllApartment();
        return vector.size();
    }

//    public static void main(String[] args) {
//        Apartment a = new Apartment();
//        ApartmentDAO dao = new ApartmentDAO();
//        Vector<Apartment> vector = dao.getAllApartmentByID(1);
//        System.out.println(vector.size());
//
//    }
    ////////////////////////////////QUAN///////////////////////////////////////
    private static final Logger LOGGER = Logger.getLogger(ApartmentDAO.class.getName());

    public List<Apartment> getApartmentsByBuilding(int buildingId) {
        Connection conn = null;
        List<Apartment> apartments = new ArrayList<>();
        try {

            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Apartment WHERE buildingID = ?";

                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, buildingId);
                    try (ResultSet rs = ps.executeQuery()) {

                        while (rs.next()) {
                            Apartment apartment = new Apartment();
                            apartment.setApartmentID(rs.getInt("apartmentID"));
                            apartments.add(apartment);
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            // Ghi log lỗi nếu có vấn đề xảy ra
            LOGGER.log(Level.SEVERE, "Error retrieving apartments", ex);
        } finally {
            // Đảm bảo đóng kết nối khi hoàn thành
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Error closing connection", ex);
                }
            }
        }
        // Trả về danh sách các Apartment
        return apartments;
    }

    public static void main(String[] args) {
        // Tạo instance của lớp ApartmentDAO
        ApartmentDAO apartmentDAO = new ApartmentDAO();

        // Giả sử bạn có một buildingID hợp lệ, ví dụ là 1
        int buildingId = 1; // Thay đổi giá trị này thành ID tòa nhà bạn muốn kiểm tra

        // Gọi phương thức getApartmentsByBuilding() để lấy danh sách các căn hộ
        List<Apartment> apartments = apartmentDAO.getApartmentsByBuilding(buildingId);

        // Kiểm tra nếu danh sách không rỗng
        if (apartments != null && !apartments.isEmpty()) {
            // In ra danh sách các căn hộ
            for (Apartment apartment : apartments) {
                System.out.println("Apartment ID: " + apartment.getApartmentID());
                // Bạn có thể in thêm các thông tin khác của apartment nếu có
            }
        } else {
            System.out.println("No apartments found for building ID: " + buildingId);
        }
    }

}
