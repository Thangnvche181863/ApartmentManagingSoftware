/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.math.BigDecimal;
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

    public static void main(String[] args) {
        
        ApartmentDAO dao = new ApartmentDAO();
        Vector<Apartment> vector = dao.getAllApartmentByID(1);
        System.out.println(vector.size());
    // public static void main(String[] args) {
    // Apartment a = new Apartment();
    // ApartmentDAO dao = new ApartmentDAO();
    // Vector<Apartment> vector = dao.getAllApartmentByID(1);
    // System.out.println(vector.size());
    //
    // }
    //////////////////////////////// QUAN///////////////////////////////////////
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
                            apartment.setBuildingID(rs.getInt("buildingID"));
                            apartment.setApartmentNumber(rs.getInt("apartmentNumber"));
                            apartment.setDepartmentType(rs.getString("departmentType"));
                            apartment.setPrice(rs.getDouble("price"));
                            apartment.setMaintenanceFee(rs.getDouble("maintenanceFee"));
                            apartment.setFloor(rs.getInt("floor"));
                            apartment.setArea(rs.getInt("area"));
                            apartments.add(apartment);
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {

            LOGGER.log(Level.SEVERE, "Error retrieving apartments", ex);
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Error closing connection", ex);
                }
            }
        }

        return apartments;
    }

    public static void main(String[] args) {
        ApartmentDAO apartmentDAO = new ApartmentDAO();
        int buildingId = 1;
        List<Apartment> apartments = apartmentDAO.getApartmentsByBuilding(buildingId);
        if (apartments != null && !apartments.isEmpty()) {

            for (Apartment apartment : apartments) {
                System.out.println("Apartment ID: " + apartment.getApartmentID());

            }
        } else {
            System.out.println("No apartments found for building ID: " + buildingId);
        }
    }

}
