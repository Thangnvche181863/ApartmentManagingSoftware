/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Apartment;

/**
 *
 * @author thang
 */
public class ApartmentDAO extends DBContext {

    public List<Apartment> getAll() {
        List<Apartment> list = new ArrayList<>();

        try {
            String sql = "Select * from Apartment";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Apartment(rs.getInt("apartmentID"), rs.getInt("buildingID"), rs.getString("departmentType"), rs.getDouble("price"), rs.getInt("floor"), rs.getInt("area")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertApartment(int buildingId, String departmentType, double price, int floor, int area) {
        try {
            String sql = "Insert into Apartment(buildingID,departmentType,price,floor,area) values(?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, buildingId);
            ps.setString(2, departmentType);
            ps.setDouble(3, price);
            ps.setInt(4, floor);
            ps.setInt(5, area);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteApartment(int apartmentId) {
        try {
            String sql = "DELETE FROM [dbo].[Apartment]\n"
                    + "      WHERE apartmentId = ? ";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, apartmentId);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateApartment(int apartmentId, int buildingId, String departmentType, double price, int floor, int area) {
        try {
            String sql = "UPDATE [dbo].[Apartment]\n"
                    + "   SET [buildingID] = ?\n"
                    + "      ,[departmentType] = ?\n"
                    + "      ,[price] = ?\n"
                    + "      ,[floor] = ?\n"
                    + "      ,[area] = ?\n"
                    + " WHERE apartmentId = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, buildingId);
            ps.setString(2, departmentType);
            ps.setDouble(3, price);
            ps.setInt(4, floor);
            ps.setInt(5, area);
            ps.setInt(6, apartmentId);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ApartmentDAO adao = new ApartmentDAO();
//        adao.insertApartment(1,"Living Room",0,1);
//        adao.deleteApartment(1);
//        adao.updateApartment(5, 2, "Bad Room", 1.00, 2);
//        adao.deleteApartment(6);
        System.out.println(adao.getAll());

    }
}
