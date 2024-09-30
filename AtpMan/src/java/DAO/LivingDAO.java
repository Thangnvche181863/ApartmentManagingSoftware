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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
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

    public List<Integer> getAgeOfResident(int apartmentID) {
        List<Integer> list = new ArrayList<>();
        String sql = " SELECT c.age FROM Customer c\n"
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

                list.add(rs.getInt(1));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
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

    public static void main(String[] args) {
        LivingDAO dao = new LivingDAO();
        List<Integer> list = dao.getAmountOfResidentOfApartment(2);
        for (Integer amount : list) {
            System.out.println("" + amount.toString());
        }

    }
}
