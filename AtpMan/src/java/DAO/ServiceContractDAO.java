/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.*;
import utils.DBContext;

/**
 *
 * @author thang
 */
public class ServiceContractDAO   {
    Connection connection= null;
    public List<ServiceContract> getAllServiceByAparmentID(int apartmentID) throws ClassNotFoundException {
        List<ServiceContract> list = new ArrayList<>();
        String sql = "select * from ServiceContract sc, Service s where sc.serviceID = s.serviceID and apartmentID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ServiceContract serviceContract = new ServiceContract();
                Service service = new Service();

                service.setServiceId(rs.getInt("serviceID"));
                service.setName(rs.getString("name"));
                service.setType(rs.getString("type"));
                service.setDescription(rs.getString("description"));
                service.setFee(rs.getBigDecimal("fee"));

                serviceContract.setServiceContractId(rs.getInt("serviceContractID"));
                serviceContract.setApartmentId(rs.getInt("apartmentID"));
                serviceContract.setService(service);
                serviceContract.setStartDate(rs.getDate("startDate"));
                serviceContract.setEndDate(rs.getDate("endDate"));
                serviceContract.setAmount(rs.getDouble("amount"));

                list.add(serviceContract);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<ServiceContract> getAll(){
        List<ServiceContract> list = new ArrayList<>();
        
        try {
            String sql = "Select * from ServiceContract";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new ServiceContract(rs.getInt("serviceContractID"),rs.getInt("apartmentID"), rs.getInt("serviceID"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getDouble("amount"), null));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertServiceContract(int apartmentId, int serviceId, Date startDate, Date endDate, double amount) {
        try {
            String sql = "Insert into ServiceContract values(?,?,?,?,?)";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, apartmentId);
            ps.setInt(2, serviceId);
            ps.setDate(3, startDate);
            ps.setDate(4, endDate);
            ps.setDouble(5, amount);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteServiceContract(int apartmentId, int serviceId) {
        try {
            String sql = "Delete * from ServiceContract where apartmentId = ? AND serviceId = ?";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, apartmentId);
            ps.setInt(2, serviceId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
       ServiceContractDAO sdao = new ServiceContractDAO();
//       sdao.insertServiceContract(7, 1, Date.valueOf("2004-07-08"), Date.valueOf("2004-03-12"), 1);
        System.out.println(sdao.getAll());
    }
}
