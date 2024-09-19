/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Building;

/**
 *
 * @author thang
 */
public class BuildingDAO extends DBContext {

    public List<Building> getAll() {
        List<Building> list = new ArrayList<>();

        try {
            String sql = "Select * from Building";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Building(rs.getInt("buildingID"), rs.getString("name"), rs.getInt("numFloor"), rs.getInt("numApartment"), rs.getString("address")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertBuilding(String name, int numFloor, int numApartment, String address) {
        try {
            String sql = "Insert into Building(name,numFloor,numApartment,address) values(?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, numFloor);
            ps.setInt(3, numApartment);
            ps.setString(4, address);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteBuilding(int buildingId) {
        try {
            String sql = "DELETE FROM [dbo].[Building]\n"
                    + "      WHERE buildingId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, buildingId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateBuilding(int buildingId, String name, int numFloor, int numApartment, String address) {
        try {
            String sql = "UPDATE [dbo].[Building]\n"
                    + "   SET [name] = ?\n"
                    + "      ,[numFloor] = ?\n"
                    + "      ,[numApartment] = ?\n"
                    + "      ,[address] = ?\n"
                    + " WHERE buildingId = ?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(2, name);
            ps.setInt(2, numFloor);
            ps.setInt(3, numApartment);
            ps.setString(4, address);
            ps.setInt(5, buildingId);
            
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        BuildingDAO bdao = new BuildingDAO();
//        bdao.insertBuilding("FTelecom", 4, 40, "Hoa Lac");
//        bdao.deleteBuilding(2);
        System.out.println(bdao.getAll());
    }

}
