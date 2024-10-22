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
import java.util.LinkedHashMap;
import java.util.List;
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

    public Building getBuildingByApartmentID(int apartmentID) {
        Connection connection = null;
        String sql = "select b.* from Building b, Apartment a\n"
                + "where a.buildingID = b.buildingID and a.apartmentID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Building building = new Building();
                building.setBuildingID(rs.getInt(1));
                building.setName(rs.getString(2));
                building.setNumFloor(rs.getInt(3));
                building.setNumApartment(rs.getInt(4));
                building.setAddress(rs.getString(5));
                return building;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Building> getAllBuildingByOwnership(int customerID) {
        List<Building> list = new ArrayList<>();
        ApartmentDAO apartmentDAO = new ApartmentDAO();
        Connection connection = null;
        String sql = "select b.*, a.* from Building b\n"
                + "inner join Apartment a on a.buildingID = b.buildingID\n"
                + "inner join Ownership o on o.apartmentID = a.apartmentID\n"
                + "where o.customerID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerID);
            ResultSet rs = statement.executeQuery();

            LinkedHashMap<Integer, Building> map = new LinkedHashMap<>();

            while (rs.next()) {
                int buildingID = rs.getInt(1);
                Building building = map.get(buildingID);

                if (building == null) {
                    building = new Building();
                    building.setBuildingID(buildingID);
                    building.setName(rs.getString(2));
                    building.setNumFloor(rs.getInt(3));
                    building.setNumApartment(rs.getInt(4));
                    building.setAddress(rs.getString(5));
                    building.setApartmentList(new ArrayList<>());
                    map.put(buildingID, building);
                }
                Apartment apartment = new Apartment();
                apartment.setApartmentID(rs.getInt(6));
                apartment.setBuildingID(buildingID);
                apartment.setApartmentNumber(rs.getString(8));
                apartment.setApartmentType(rs.getString(9));
                apartment.setPrice(rs.getBigDecimal(10));
                apartment.setMaintenanceFee(rs.getBigDecimal(11));
                apartment.setFloor(rs.getInt(12));
                apartment.setArea(rs.getInt(13));

                building.getApartmentList().add(apartment);
            }
            list.addAll(map.values());
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
//        Building building = new Building();
        BuildingDAO dao = new BuildingDAO();
        Vector<Building> vector = dao.getAllBuilding();
        System.out.println(vector.size());
        
        List<Building> bList = dao.getAllBuildingByOwnership(1);
        System.out.println(bList.get(0).getApartmentList().get(0).getApartmentNumber());
    }

}
