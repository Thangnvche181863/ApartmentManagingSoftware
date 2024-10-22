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
import model.Apartment;
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
                String apartmentType = rs.getString(4);
                BigDecimal price = rs.getBigDecimal(5);
                BigDecimal maintainanceFee = rs.getBigDecimal(6);
                int floor = rs.getInt(7);
                int area = rs.getInt(8);
                Apartment apartment = new Apartment(apartmentID, buildingID, apartmentNumber, apartmentType, price, maintainanceFee, floor, area);
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
                String apartmentType = rs.getString(4);
                BigDecimal price = rs.getBigDecimal(5);
                BigDecimal maintainanceFee = rs.getBigDecimal(6);
                int floor = rs.getInt(7);
                int area = rs.getInt(8);
                Apartment apartment = new Apartment(apartmentID, buildingID, apartmentNumber, apartmentType, price, maintainanceFee, floor, area);
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

    public Apartment getApartmentByCustomerId(int customerId) {
        Connection connection = null;
        String sql = "select a.* from Apartment a\n"
                + "inner join Living l on a.apartmentID = l.apartmentID\n"
                + "where l.customerID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Apartment apartment = new Apartment();
                apartment.setApartmentID(rs.getInt(1));
                apartment.setBuildingID(rs.getInt(2));
                apartment.setApartmentNumber(rs.getString(3));
                apartment.setApartmentType(rs.getString(4));
                apartment.setPrice(rs.getBigDecimal(5));
                apartment.setMaintenanceFee(rs.getBigDecimal(6));
                apartment.setFloor(rs.getInt(7));
                apartment.setArea(rs.getInt(8));
                return apartment;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public Apartment getApartmentByID(int apartmentID) {
        Connection connection = null;
        String sql = "select * from Apartment where apartmentID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Apartment apartment = new Apartment();
                apartment.setApartmentID(rs.getInt(1));
                apartment.setBuildingID(rs.getInt(2));
                apartment.setApartmentNumber(rs.getString(3));
                apartment.setApartmentType(rs.getString(4));
                apartment.setPrice(rs.getBigDecimal(5));
                apartment.setMaintenanceFee(rs.getBigDecimal(6));
                apartment.setFloor(rs.getInt(7));
                apartment.setArea(rs.getInt(8));
                return apartment;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Apartment> getAllApartmentByOwner(int customerID) {
        Connection connection = null;
        List<Apartment> list = new ArrayList<>();
        String sql = "select a.* from apartment a\n"
                + "inner join Ownership o on a.apartmentID = o.apartmentID\n"
                + "where o.customerID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int apartmentID = rs.getInt(1);
                int buildingID = rs.getInt(2);
                String apartmentNumber = rs.getString(3);
                String apartmentType = rs.getString(4);
                BigDecimal price = rs.getBigDecimal(5);
                BigDecimal maintainanceFee = rs.getBigDecimal(6);
                int floor = rs.getInt(7);
                int area = rs.getInt(8);
                Apartment apartment = new Apartment(apartmentID, buildingID, apartmentNumber, apartmentType, price, maintainanceFee, floor, area);
                list.add(apartment);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        Apartment a = new Apartment();
        ApartmentDAO dao = new ApartmentDAO();
        Vector<Apartment> vector = dao.getAllApartment();

        System.out.println(vector.size());

        a = dao.getApartmentByCustomerId(1);
        System.out.println(a.getArea());
    }
}
