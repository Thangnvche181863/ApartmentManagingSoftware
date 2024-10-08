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

    public Apartment getApartmentByCustomerId(int customerId) {
        Connection connection = null;
        Apartment apartment = new Apartment();
        String sql = "select a.* from Apartment a\n"
                + "inner join Living l on a.apartmentID = l.apartmentID\n"
                + "where l.customerID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                apartment.setApartmentID(rs.getInt(1));
                apartment.setBuildingID(rs.getInt(2));
                apartment.setDepartmentType(rs.getString(3));
                apartment.setPrice(rs.getDouble(4));
                apartment.setFloor(rs.getInt(5));
                apartment.setArea(rs.getInt(6));
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
        return apartment;
    }

    public static void main(String[] args) {
        Apartment a = new Apartment();
        ApartmentDAO dao = new ApartmentDAO();
        Vector<Apartment> vector = dao.getAllApartmentByID(1);
        System.out.println(vector.size());
        
        a = dao.getApartmentByCustomerId(1);
        System.out.println(a.getArea());
    }
}
