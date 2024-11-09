/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
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
                ServiceContract sc = new ServiceContract();
                sc.setServiceContractId(rs.getInt(1));
                sc.setApartmentId(rs.getInt(2));
                sc.setServiceId(rs.getInt(3));
                sc.setStartDate(rs.getDate(4));
                sc.setEndDate(rs.getDate(5));
                sc.setAmount(rs.getBigDecimal(6));
                list.add(sc);
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
            String sql = "Delete from ServiceContract where apartmentId = ? AND serviceId = ?";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, apartmentId);
            ps.setInt(2, serviceId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<ServiceContract> getCurrentServiceContract(int apartmentId, Date currentDate) throws ClassNotFoundException {
        List<ServiceContract> list = new ArrayList<>();
        String sql = "select * from ServiceContract sc\n"
                + "inner join Service s on sc.serviceID = s.serviceID\n"
                + "where sc.apartmentID = ? and (? between sc.startDate and sc.endDate)";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentId);
            statement.setDate(2, currentDate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // declare service object
                Service service = new Service();

                service.setServiceId(rs.getInt("serviceID"));
                service.setName(rs.getString("name"));
                service.setType(rs.getString("type"));
                service.setDescription(rs.getString("description"));
                service.setFee(rs.getBigDecimal("fee"));
                service.setImg(rs.getString("img"));
                service.setIcon(rs.getString("icon"));

                // declare serviceContract object
                ServiceContract serviceContract = new ServiceContract();

                serviceContract.setServiceContractId(rs.getInt("serviceContractID"));
                serviceContract.setApartmentId(rs.getInt("apartmentID"));
                serviceContract.setService(service);
                serviceContract.setStartDate(rs.getDate("startDate"));
                serviceContract.setEndDate(rs.getDate("endDate"));
                serviceContract.setAmount(rs.getBigDecimal("amount"));

                list.add(serviceContract);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<ServiceContract> serviceContractById(int apartmentId, int month, int year) {
        List<ServiceContract> scs = new ArrayList<>();
        ServiceDAO sdao = new ServiceDAO();
        List<Service> services = sdao.getAll();
        try {
            String sql = "select * from ServiceContract where apartmentId = ? and MONTH(startDate) = ? and YEAR(startDate) = ?";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, apartmentId);
            ps.setInt(2, month);
            ps.setInt(3, year);
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

    public ServiceContract statisticContract(int id, int month, int year) {
        ServiceContract sc = new ServiceContract();
        try {
            String sql = "SELECT \n"
                    + "    s.apartmentID, \n"
                    + "    COUNT(serviceContractID) AS totalContracts, \n"
                    + "    SUM(amount) AS totalAmount\n"
                    + "FROM \n"
                    + "    ServiceContract s where apartmentID = ? AND MONTH(s.startDate) = ? AND YEAR(s.startDate) = ?\n"
                    + "GROUP BY \n"
                    + "    apartmentID";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, month);
            ps.setInt(3, year);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                sc.setApartmentId(rs.getInt(1));
                sc.setTotalContract(rs.getInt(2));
                sc.setTotalAmount(rs.getBigDecimal(3));
            } else {
                // Không có hợp đồng dịch vụ nào được tìm thấy, thiết lập `totalAmount` = 0
                sc.setApartmentId(id);
                sc.setTotalContract(0);
                sc.setTotalAmount(BigDecimal.ZERO);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sc;
    }

    public List<Service> unregisteredService(int apartmentID, Date currentDate) {
        List<Service> list = new ArrayList<>();
        try {
            String sql = "SELECT s.* \n"
                    + "FROM Service s\n"
                    + "LEFT JOIN ServiceContract sc \n"
                    + "ON s.serviceID = sc.serviceID \n"
                    + "   AND sc.apartmentID = ? \n"
                    + "   AND ? BETWEEN sc.startDate AND sc.endDate\n"
                    + "WHERE sc.serviceID IS NULL";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, apartmentID);
            ps.setDate(2, currentDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service s = new Service();
                s.setServiceId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setType(rs.getString(3));
                s.setDescription(rs.getString(4));
                s.setImg((rs.getString(5)));
                s.setIcon(rs.getString(6));
                s.setFee(rs.getBigDecimal(7));

                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ServiceContract pickServiceContract(int serviceID) {
        ServiceContract sc = new ServiceContract();
        String sql = "select * from ServiceContract where serviceID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, serviceID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sc.setTotalAmount(rs.getBigDecimal(6));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sc;
    }

    public BigDecimal totalBuildingFinance(int month, int year) {
        BigDecimal total = BigDecimal.ZERO;
        String sql = "SELECT SUM(amount) AS total\n"
                + "FROM ServiceContract\n"
                + "WHERE MONTH(startDate) = ? and YEAR(startDate) = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getBigDecimal(1);
                // Nếu kết quả từ truy vấn là null, gán giá trị là BigDecimal.ZERO
                if (total == null) {
                    total = BigDecimal.ZERO;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ServiceContractDAO sdao = new ServiceContractDAO();
        ////       sdao.insertServiceContract(7, 1, Date.valueOf("2004-07-08"), Date.valueOf("2004-03-12"), 1);
        // List<ServiceContract> list = sdao.getCurrentServiceContract(1, Date.valueOf(LocalDate.now()));
        // System.out.println(list);
//        List<ServiceContract> list = sdao.getAll();
//        System.out.println(sdao.totalBuildingFinance(10, 2024));
//        System.out.println(sdao.totalBuildingFinance(10, 2024));
//sdao.deleteServiceContract(1, 1);
//        for (ServiceContract sc : list) {
//            if (sc.getServiceId() == 1 && sc.getApartmentId() == 1) {
//                System.out.println(sc);
//                }
//        }
        //
////        System.out.println(sdao.serviceContractById(1));
//        System.out.println(sdao.statisticContract(1).getTotalAmount());
        System.out.println(sdao.unregisteredService(1,Date.valueOf(LocalDate.now())));
    }
}
