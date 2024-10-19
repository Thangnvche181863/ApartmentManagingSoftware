/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
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
                String apartmentNumber = rs.getString(3);
                String departmentType = rs.getString(4);
                BigDecimal price = rs.getBigDecimal(5);
                BigDecimal maintenanceFee = rs.getBigDecimal(6);
                int floor = rs.getInt(7);
                int area = rs.getInt(8);
                Apartment apartment = new Apartment(apartmentID, buildingID, apartmentNumber, departmentType, price, maintenanceFee, floor, area);
                vector.add(apartment);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    //thang
    public Vector<Building> getAllBuilding() {
        Connection conn = null;
        Vector<Building> vector = new Vector<>();
        String sql = "select * from Building";
        try {
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vector.add(new Building(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return vector;
    }



    //thang
    public Vector<Apartment> getAllDepartmentType() {
        Connection conn = null;
        Vector<Apartment> vector = new Vector<>();
        String sql = "select a.departmentType\n"
                + "from Apartment a\n"
                + "group by a.departmentType";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                vector.add(new Apartment(rs.getString(1)));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    //thang
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
                String apartmentNumber = rs.getString(3);
                String departmentType = rs.getString(4);
                BigDecimal price = rs.getBigDecimal(5);
                BigDecimal maintenanceFee = rs.getBigDecimal(6);
                int floor = rs.getInt(7);
                int area = rs.getInt(8);
                Apartment apartment = new Apartment(apartmentID, buildingID, apartmentNumber, departmentType, price, maintenanceFee, floor, area);
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
    
        //thang
    public Vector<Apartment> allApartmentPaging(int page, int recordsPerPage, String buildingtype, String departmenttype, String search, String orderBy) {
        Vector<Apartment> vector = new Vector<>();
        Connection conn = null;

        StringBuilder sql = new StringBuilder("SELECT A.apartmentID, b.name AS buildingName, A.apartmentNumber, A.departmentType, A.floor, ");
        sql.append("CASE WHEN SUM(SC.amount) IS NULL THEN 0 ELSE SUM(SC.amount) END AS totalAmount ")
                .append("FROM Apartment A ")
                .append("LEFT JOIN ServiceContract SC ON A.apartmentID = SC.apartmentID ")
                .append("LEFT JOIN Service S ON SC.serviceID = S.serviceID ")
                .append("LEFT JOIN Building b ON A.buildingID = b.buildingID ")
                .append("WHERE 1=1 ");

        // Thêm điều kiện lọc nếu cần
        if (buildingtype != null && !buildingtype.isEmpty()) {
            sql.append("AND b.name = ? ");
        }
        if (departmenttype != null && !departmenttype.isEmpty()) {
            sql.append("AND A.departmentType = ? ");
        }
        if (search != null && !search.isEmpty()) {
            sql.append("AND A.apartmentNumber COLLATE Latin1_General_CI_AI LIKE ? ");
        }
        if (orderBy != null && !orderBy.isEmpty()) {
            sql.append("GROUP BY A.apartmentID, b.name, A.apartmentNumber, A.departmentType, A.floor ")
                    .append("ORDER BY totalAmount ").append(orderBy).append(" ");
        } else {
            sql.append("GROUP BY A.apartmentID, b.name, A.apartmentNumber, A.departmentType, A.floor ")
                    .append("ORDER BY A.apartmentID ");
        }
        sql.append("OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try {
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (buildingtype != null && !buildingtype.isEmpty()) {
                ps.setString(paramIndex++, buildingtype);
            }
            if (departmenttype != null && !departmenttype.isEmpty()) {
                ps.setString(paramIndex++, departmenttype);
            }
            if (search != null && !search.isEmpty()) {
                ps.setString(paramIndex++, "%" + search + "%");
            }
            ps.setInt(paramIndex++, (page - 1) * recordsPerPage);
            ps.setInt(paramIndex, recordsPerPage);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Apartment a = new Apartment();
                a.setApartmentID(rs.getInt("apartmentID"));
                a.setApartmentNumber(rs.getString("apartmentNumber"));
                a.setDepartmentType(rs.getString("departmentType"));
                a.setFloor(rs.getInt("floor"));
                a.setTotalAmount(rs.getBigDecimal("totalAmount"));
                a.setName(rs.getString("buildingName"));
                vector.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return vector;
    }
    
        public int getTotalApartment( String buildingtype, String departmenttype, String search, String orderBy) {
        ApartmentDAO dao = new ApartmentDAO();
        Vector<Apartment> vector = dao.allApartmentPaging(1, dao.getAmountOfApartment(), buildingtype, departmenttype, search, orderBy);
        return vector.size();
    }




    public static void main(String[] args) {
        Apartment a = new Apartment();
        ApartmentDAO dao = new ApartmentDAO();
//        Vector<Apartment> vector = dao.getAllApartmentByID(1);
//        System.out.println(vector.size());
        Vector<Apartment> apartments = dao.allApartmentPaging(1, 25, "", "", "", "");

        System.out.println(dao.getTotalApartment("tòa Alpha", "", "", ""));
    }
}
