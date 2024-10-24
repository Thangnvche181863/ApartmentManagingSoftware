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
import java.util.List;
import java.util.Vector;
import model.Apartment;
import model.Building;
import model.ServiceContract;
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
                Apartment a = new Apartment();
                a.setApartmentID(rs.getInt(1));
                a.setBuildingID(rs.getInt(2));
                a.setApartmentNumber(rs.getString(3));
                a.setApartmentType(rs.getString(4));
                a.setPrice(rs.getBigDecimal(5));
                a.setMaintenanceFee(rs.getBigDecimal(6));
                a.setFloor(rs.getInt(7));
                a.setArea(rs.getInt(8));
     
                vector.add(a);
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
        String sql = "select a.apartmentType\n"
                + "from Apartment a\n"
                + "group by a.apartmentType";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Apartment a = new Apartment();
                a.setApartmentType(rs.getString(1));
                vector.add(a);
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
                
                Apartment a = new Apartment();
                a.setApartmentID(rs.getInt(1));
                a.setBuildingID(rs.getInt(2));
                a.setApartmentNumber(rs.getString(3));
                a.setApartmentType(rs.getString(4));
                a.setPrice(rs.getBigDecimal(5));
                a.setMaintenanceFee(rs.getBigDecimal(6));
                a.setFloor(rs.getInt(7));
                a.setArea(rs.getInt(8));
                 
                vector.add(a);
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
    public Vector<Apartment> allApartmentPaging(int page, int recordsPerPage, String buildingtype, String apartmentType, String search, String orderBy) {
        Vector<Apartment> vector = new Vector<>();
        Connection conn = null;

        StringBuilder sql = new StringBuilder("SELECT A.apartmentID, b.name AS buildingName, A.apartmentNumber, A.apartmentType, A.floor, ");
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
        if (apartmentType != null && !apartmentType.isEmpty()) {
            sql.append("AND A.apartmentType = ? ");
        }
        if (search != null && !search.isEmpty()) {
            sql.append("AND A.apartmentNumber COLLATE Latin1_General_CI_AI LIKE ? ");
        }
        if (orderBy != null && !orderBy.isEmpty()) {
            sql.append("GROUP BY A.apartmentID, b.name, A.apartmentNumber, A.apartmentType, A.floor ")
                    .append("ORDER BY totalAmount ").append(orderBy).append(" ");
        } else {
            sql.append("GROUP BY A.apartmentID, b.name, A.apartmentNumber, A.apartmentType, A.floor ")
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
            if (apartmentType != null && !apartmentType.isEmpty()) {
                ps.setString(paramIndex++, apartmentType);
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
                a.setApartmentType(rs.getString("apartmentType"));
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

    public int getTotalApartment(String buildingtype, String apartmentType, String search, String orderBy) {
        ApartmentDAO dao = new ApartmentDAO();
        Vector<Apartment> vector = dao.allApartmentPaging(1, dao.getAmountOfApartment(), buildingtype, apartmentType, search, orderBy);
        return vector.size();
    }

    public Apartment apartmentDetail(int id) {
        Connection conn = null;
        Apartment apartment = new Apartment();
        ServiceContractDAO scdao = new ServiceContractDAO();
        BuildingDAO bdaos = new BuildingDAO();
        try {
            String sql = "SELECT * FROM Apartment WHERE apartmentID = ?";
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                apartment.setApartmentID(rs.getInt(1));
                apartment.setName(bdaos.getBuildingName(rs.getInt(2)));
                apartment.setApartmentNumber(rs.getString(3));
                apartment.setApartmentType(rs.getString(4));
                apartment.setPrice(rs.getBigDecimal(5));
                apartment.setMaintenanceFee(rs.getBigDecimal(6));
                apartment.setFloor(rs.getInt(7));
                apartment.setArea(rs.getInt(8));
                apartment.setList(scdao.serviceContractById(rs.getInt(1)));
                
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return apartment;
    }

    public static void main(String[] args) {
        ApartmentDAO dao = new ApartmentDAO();
        Apartment a =  dao.apartmentDetail(1);
//        System.out.println(a.getList().get(0).getService().getFee());
        System.out.println(dao.allApartmentPaging(1, 25, "", "", "", "asc"));
//        System.out.println(dao.getAllApartment());
    }
}
