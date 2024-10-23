/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author thang
 */

public class ServiceDAO extends DBContext {

    public List<Service> getAllService() {
        List<Service> list = new ArrayList<>();
        String sql = "select * from Service";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Service service = new Service();
                service.setServiceId(rs.getInt(1));
                service.setName(rs.getString(2));
                service.setType(rs.getString(3));
                service.setDescription(rs.getString(4));
                service.setFee(rs.getBigDecimal(5));
                list.add(service);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    
    public List<Service> getAll() {
        List<Service> list = new ArrayList<>();
        try {

            String sql = "Select * from Service";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Service(rs.getInt("serviceID"), rs.getString("name"), rs.getString("type"), rs.getBigDecimal("fee"), rs.getString("description"), rs.getString("img"), rs.getString("icon")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Service findById(int id){
        List<Service> list = getAll();
        for (Service service : list) {
            if(service.getServiceId() == id) return service;
        }
        return null;
    }
    
    public int update(Service service){
        List<Service> list = getAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getServiceId() == service.getServiceId()){
                list.set(i, service);
                return i;
            }
        }
        return -1;
    }
    
    

    public void insertService(String name, String type, BigDecimal fee, String description, String img, String icon) {
        try {
            String sql = "Insert into Service(name,type,fee,description,img,icon) values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setBigDecimal(3, fee);
            ps.setString(4, description);
            ps.setString(5, img);
            ps.setString(6, icon);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateService(int serviceId, String name, String type, BigDecimal fee, String description,String img, String icon) {
        try {
            String sql = "UPDATE [dbo].[Service]\n"
                    + "   SET [name] = ?\n"
                    + "      ,[type] = ?\n"
                    + "      ,[fee] = ?\n"
                    + "      ,[description] = ?\n"
                    + "      ,[img] = ?\n"
                    + "      ,[icon] = ?\n"
                    + " WHERE serviceID = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setBigDecimal(3, fee);
            ps.setString(4, description);
            ps.setString(5, img);
            ps.setString(6, icon);
            ps.setInt(7, serviceId);

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

//        sdao.insertService("Finance manager", type, 0, description);
//        sdao.insertService("Monthly Maintaince", "Apartment Fee", 150.00);
//        sdao.insertService("Gym Membership", "Amenity", 150.00);
//('Gym Membership', 'Amenity', 200.00),
//('Swimming Pool Access', 'Amenity', 120.00),
//('Laundry Service', 'Convenience', 80.00),
//('Repair Service', 'Maintenance', 250.00);
        System.out.println(sdao.getAll());
    }
}
