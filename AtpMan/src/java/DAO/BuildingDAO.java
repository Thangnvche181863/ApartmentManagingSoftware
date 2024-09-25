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
import model.Building;
import utils.DBContext;

/**
 *
 * @author Admin
 */
public class BuildingDAO {

    public Vector<Building> getAllBuilding() {
        Connection conn = null;
        Vector<Building> vector = new Vector<>();
        String sql = "select * from Building";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int buildingID = rs.getInt(1);
                String name = rs.getString(2);
                int numFloor = rs.getInt(3);
                int numApartment = rs.getInt(4);
                String address = rs.getString(5);
                Building building = new Building(buildingID, name, numFloor, numApartment, address);
                vector.add(building);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int getAmountOfBuilding() {
        Building building = new Building();
        BuildingDAO dao = new BuildingDAO();
        Vector<Building> vector = dao.getAllBuilding();
        return vector.size();
    }

    public static void main(String[] args) {
        Building building = new Building();
        BuildingDAO dao = new BuildingDAO();
        Vector<Building> vector = dao.getAllBuilding();
        System.out.println(vector.size());
    }

    public Vector<Integer> getApartmentAvailable() {
        Vector<Integer> vector = new Vector<>();
        String sql = "SELECT \n"
                + "    b.buildingID,\n"
                + "    COUNT(a.apartmentID) AS totalApartments,\n"
                + "    COUNT(l.apartmentID) AS totalLivingApartments,\n"
                + "    COUNT(a.apartmentID) - COUNT(l.apartmentID) AS availableApartments\n"
                + "FROM \n"
                + "    Building b\n"
                + "LEFT JOIN \n"
                + "    Apartment a ON b.buildingID = a.buildingID\n"
                + "LEFT JOIN \n"
                + "    Living l ON a.apartmentID = l.apartmentID\n"
                + "GROUP BY \n"
                + "    b.buildingID;";

        Connection conn = null;

        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {

                vector.add(rs.getInt(4));
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

        return vector;
    }

}
