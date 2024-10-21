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
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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

//    public static void main(String[] args) {
//        Building building = new Building();
//        BuildingDAO dao = new BuildingDAO();
//        Vector<Building> vector = dao.getAllBuilding();
//        System.out.println(vector.size());
//        for(Building building1 : vector){
//            System.out.println();
//        }
//    }
    ////////////////////////////////QUAN///////////////////////////////////////
    private static final Logger LOGGER = Logger.getLogger(BuildingDAO.class.getName());

    public List<Building> getAllBuildings() {
        Connection conn = null;
        List<Building> buildings = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Failed to connect to the database.");
            }

            if (conn != null) {
                String sql = "SELECT * FROM Building";
                try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Building building = new Building();
                        building.setBuildingID(rs.getInt("buildingID"));
                        building.setName(rs.getString("name"));
                        building.setNumFloor(rs.getInt("numFloor"));
                        building.setNumApartment(rs.getInt("numApartment"));
                        building.setAddress(rs.getString("address"));
                        buildings.add(building);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving buildings", ex);
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Error closing connection", ex);
                }
            }
        }
        return buildings;
    }

    public String getBuildingName(int id) {
        Connection conn = null;
        String name = "";
        try {
            String sql = "SELECT * FROM Building WHERE buildingID = ?";
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return name;
    }

    public static void main(String[] args) {

        BuildingDAO buildingDAO = new BuildingDAO();
        System.out.println(buildingDAO.getBuildingName(1));

    }
}
