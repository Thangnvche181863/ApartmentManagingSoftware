/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Service;

/**
 *
 * @author thang
 */
public class ServiceDAO extends DBContext {

    public List<Service> getAll() {
        List<Service> list = new ArrayList<>();
        try {

            String sql = "Select * from Service";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Service(rs.getInt("serviceID"), rs.getString("name"), rs.getString("type"), rs.getDouble("fee"), rs.getString("description")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertService(String name, String type, double fee, String description) {
        try {
            String sql = "Insert into Service(name,type,fee,description) values(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setDouble(3, fee);
            ps.setString(4, description);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateService(int serviceId, String name, String type, double fee, String description) {
        try {
            String sql = "UPDATE [dbo].[Service]\n"
                    + "   SET [name] = ?\n"
                    + "      ,[type] = ?\n"
                    + "      ,[fee] = ?\n"
                    + "      ,[description] = ?\n"
                    + " WHERE serviceID = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setDouble(3, fee);
            ps.setString(4, description);
            ps.setInt(5, serviceId);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteService(int serviceId) {
        try {
            String sql = "DELETE FROM [dbo].[Service]\n"
                    + "      WHERE serviceID = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, serviceId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ServiceDAO sdao = new ServiceDAO();
//        sdao.insertService("Monthly Maintaince", "Apartment Fee", 150.00);
//        sdao.insertService("Gym Membership", "Amenity", 150.00);
//('Gym Membership', 'Amenity', 200.00),
//('Swimming Pool Access', 'Amenity', 120.00),
//('Laundry Service', 'Convenience', 80.00),
//('Repair Service', 'Maintenance', 250.00);
        System.out.println(sdao.getAll());
    }
}
