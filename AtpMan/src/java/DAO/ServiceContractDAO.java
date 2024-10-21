/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;
import utils.DBContext;

/**
 *
 * @author thang
 */
public class ServiceContractDAO {

    Connection connection = null;

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
                serviceContract.setAmount(rs.getBigDecimal("amount"));

                list.add(serviceContract);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<ServiceContract> getAll() {
        List<ServiceContract> list = new ArrayList<>();

        try {
            String sql = "Select * from ServiceContract";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ServiceContract(rs.getInt("serviceContractID"), rs.getInt("apartmentID"), rs.getInt("serviceID"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getBigDecimal("amount"), null));
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

    public List<ServiceContract> serviceContractById(int apartmentId) {
        List<ServiceContract> scs = new ArrayList<>();
        ServiceDAO sdao = new ServiceDAO();
        List<Service> services = sdao.getAll();
        try {
            String sql = "Select * from ServiceContract where apartmentId = ?";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, apartmentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ServiceContract sc = new ServiceContract();
                sc.setServiceContractId(rs.getInt("serviceContractID"));
                sc.setApartmentId(rs.getInt("apartmentID"));
                sc.setServiceId(rs.getInt("serviceID"));
                sc.setStartDate(rs.getDate("startDate"));
                sc.setEndDate(rs.getDate("endDate"));
                sc.setAmount(rs.getBigDecimal("amount"));
                sc.setService(services.get(rs.getInt("serviceID") - 1));
                scs.add(sc);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return scs;
    }

    public ServiceContract statisticContract(int id) {
        ServiceContract sc = new ServiceContract();
        try {
            String sql = "SELECT \n"
                    + "    apartmentID, \n"
                    + "    COUNT(serviceContractID) AS totalContracts, \n"
                    + "    SUM(amount) AS totalAmount\n"
                    + "FROM \n"
                    + "    ServiceContract where apartmentID = ?\n"
                    + "GROUP BY \n"
                    + "    apartmentID";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                sc.setApartmentId(rs.getInt(1));
                sc.setTotalContract(rs.getInt(2));
                sc.setTotalAmount(rs.getBigDecimal(3));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sc;
    }

    public static void main(String[] args) {
        ServiceContractDAO sdao = new ServiceContractDAO();
//       sdao.insertServiceContract(7, 1, Date.valueOf("2004-07-08"), Date.valueOf("2004-03-12"), 1);

//        System.out.println(sdao.serviceContractById(1));
        System.out.println(sdao.statisticContract(1).getTotalAmount());

    }
}
