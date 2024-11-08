/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.math.BigDecimal;
import utils.DBContext;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.*;
import java.sql.Connection;

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
            LocalDate currentDate = LocalDate.now();
            String sql = "update ServiceContract \n"
                    + "set endDate = ?\n"
                    + "where serviceContractID = ? and apartmentID = ?";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(currentDate));
            ps.setInt(2, serviceId);
            ps.setInt(3, apartmentId);
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

    public List<ServiceContract> getCurrentServiceContractNotPaging(int apartmentId, Date currentDate) throws ClassNotFoundException {
        List<ServiceContract> list = new ArrayList<>();
        String sql = "select * from ServiceContract sc\n"
                + "inner join Service s on sc.serviceID = s.serviceID\n"
                + "where sc.apartmentID = ? and (sc.startDate <= ? and ? < sc.endDate)";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentId);
            statement.setDate(2, currentDate);
            statement.setDate(3, currentDate);
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

    // KhangPM
    public List<ServiceContract> getCurrentServiceContract(int apartmentId, Date currentDate, int currentPage, int rowsPerPage, List<String> searchTermList) {
        List<ServiceContract> list = new ArrayList<>();
        String sql = "select * from ServiceContract sc\n"
                + "inner join Service s on sc.serviceID = s.serviceID\n"
                + "where sc.apartmentID = ? and (sc.startDate <= ? and ? < sc.endDate)\n";
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() <= 1) {
                //and ((s.name like N'%%' or s.type like N'%%')) in SQL
                sql += " and (s.name like N'%" + searchTermList.get(0) + "%' or s.type like N'%" + searchTermList.get(0) + "%')";
            } else {
                //and ((s.name like N'%%' and s.name like N'%%') or (s.type like N'%%' and s.type like N'%%')) in SQL
                sql += " and ((s.name like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " and s.name like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " and s.name like N'%" + searchTermList.get(searchTermList.size() - 1) + "%') ";

                sql += " or (s.type like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " and s.type like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " and s.type like N'%" + searchTermList.get(searchTermList.size() - 1) + "%')) ";
            }
        }
        sql += "order by sc.serviceContractID\n"
                + "offset ? rows fetch next ? rows only";

        int fetchStart = (currentPage - 1) * rowsPerPage;
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentId);
            statement.setDate(2, currentDate);
            statement.setDate(3, currentDate);
            statement.setInt(4, fetchStart);
            statement.setInt(5, rowsPerPage);
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
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return list;
    }

    // KhangPM
    public int countCurrentServiceContract(int apartmentId, Date currentDate, List<String> searchTermList) {
        int count = 0;
        String sql = "select count(*) from ServiceContract sc\n"
                + "inner join Service s on sc.serviceID = s.serviceID\n"
                + "where sc.apartmentID = ? and (? between sc.startDate and sc.endDate)\n";
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() <= 1) {
                //and ((s.name like N'%%' or s.type like N'%%')) in SQL
                sql += " and (s.name like N'%" + searchTermList.get(0) + "%' or s.type like N'%" + searchTermList.get(0) + "%')";
            } else {
                //and ((s.name like N'%%' and s.name like N'%%') or (s.type like N'%%' and s.type like N'%%')) in SQL
                sql += " and ((s.name like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " and s.name like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " and s.name like N'%" + searchTermList.get(searchTermList.size() - 1) + "%') ";

                sql += " or (s.type like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " and s.type like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " and s.type like N'%" + searchTermList.get(searchTermList.size() - 1) + "%')) ";
            }
        }

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentId);
            statement.setDate(2, currentDate);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return count;
    }

    // KhangPM
    public BigDecimal totalAmountCurrentServiceContract(int apartmentId, Date currentDate, List<String> searchTermList) {
        BigDecimal totalAmount = null;
        String sql = "select SUM(sc.amount) as totalAmount from ServiceContract sc\n"
                + "inner join Service s on sc.serviceID = s.serviceID\n"
                + "where sc.apartmentID = ? and (? between sc.startDate and sc.endDate)\n";
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() <= 1) {
                //and ((s.name like N'%%' or s.type like N'%%')) in SQL
                sql += " and (s.name like N'%" + searchTermList.get(0) + "%' or s.type like N'%" + searchTermList.get(0) + "%')";
            } else {
                //and ((s.name like N'%%' and s.name like N'%%') or (s.type like N'%%' and s.type like N'%%')) in SQL
                sql += " and ((s.name like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " and s.name like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " and s.name like N'%" + searchTermList.get(searchTermList.size() - 1) + "%') ";

                sql += " or (s.type like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " and s.type like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " and s.type like N'%" + searchTermList.get(searchTermList.size() - 1) + "%')) ";
            }
        }

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentId);
            statement.setDate(2, currentDate);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                totalAmount = rs.getBigDecimal(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return totalAmount;
    }

    // KhangPM
    public ServiceContract getServiceContractByInvoiceId(int invoiceId) {
        ServiceContract serviceContract = null;
        String sql = "select * from ServiceContract sc\n"
                + "inner join Service s on sc.serviceID = s.serviceID\n"
                + "inner join InvoiceService isc on isc.serviceContractID = sc.serviceContractID\n"
                + "where isc.invoiceID = ?\n";

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, invoiceId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // declare service object
                Service service = new Service();

                service.setServiceId(rs.getInt("serviceID"));
                service.setName(rs.getString("name"));
                service.setType(rs.getString("type"));
                service.setDescription(rs.getString("description"));
                service.setFee(rs.getBigDecimal("fee"));

                // declare serviceContract object
                serviceContract = new ServiceContract();

                serviceContract.setServiceContractId(rs.getInt("serviceContractID"));
                serviceContract.setApartmentId(rs.getInt("apartmentID"));
                serviceContract.setService(service);
                serviceContract.setStartDate(rs.getDate("startDate"));
                serviceContract.setEndDate(rs.getDate("endDate"));
                serviceContract.setAmount(rs.getBigDecimal("amount"));

            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return serviceContract;
    }
    // KhangPM
    public List<ServiceContract> getCurrentServiceContractByInvoiceId(int invoiceId, int currentPage, int rowsPerPage, List<String> searchTermList) {
        List<ServiceContract> list = new ArrayList<>();
        String sql = "select * from ServiceContract sc\n"
                + "inner join Service s on sc.serviceID = s.serviceID\n"
                + "inner join InvoiceService isc on isc.serviceContractID = sc.serviceContractID\n"
                + "where isc.invoiceID = ?\n";
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() <= 1) {
                //and ((s.name like N'%%' or s.type like N'%%')) in SQL
                sql += " and (s.name like N'%" + searchTermList.get(0) + "%' or s.type like N'%" + searchTermList.get(0) + "%')";
            } else {
                //and ((s.name like N'%%' and s.name like N'%%') or (s.type like N'%%' and s.type like N'%%')) in SQL
                sql += " and ((s.name like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " and s.name like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " and s.name like N'%" + searchTermList.get(searchTermList.size() - 1) + "%') ";

                sql += " or (s.type like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " and s.type like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " and s.type like N'%" + searchTermList.get(searchTermList.size() - 1) + "%')) ";
            }
        }
        sql += "order by sc.serviceContractID\n"
                + "offset ? rows fetch next ? rows only";

        int fetchStart = (currentPage - 1) * rowsPerPage;
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, invoiceId);
            statement.setInt(2, fetchStart);
            statement.setInt(3, rowsPerPage);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // declare service object
                Service service = new Service();

                service.setServiceId(rs.getInt("serviceID"));
                service.setName(rs.getString("name"));
                service.setType(rs.getString("type"));
                service.setDescription(rs.getString("description"));
                service.setFee(rs.getBigDecimal("fee"));

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
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return list;
    }

    // KhangPM
    public int countCurrentServiceContractByInvoiceId(int invoiceId, List<String> searchTermList) {
        int count = 0;
        String sql = "select count(*) from ServiceContract sc\n"
                + "inner join Service s on sc.serviceID = s.serviceID\n"
                + "inner join InvoiceService isc on isc.serviceContractID = sc.serviceContractID\n"
                + "where isc.invoiceID = ?\n";
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() <= 1) {
                //and ((s.name like N'%%' or s.type like N'%%')) in SQL
                sql += " and (s.name like N'%" + searchTermList.get(0) + "%' or s.type like N'%" + searchTermList.get(0) + "%')";
            } else {
                //and ((s.name like N'%%' and s.name like N'%%') or (s.type like N'%%' and s.type like N'%%')) in SQL
                sql += " and ((s.name like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " and s.name like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " and s.name like N'%" + searchTermList.get(searchTermList.size() - 1) + "%') ";

                sql += " or (s.type like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " and s.type like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " and s.type like N'%" + searchTermList.get(searchTermList.size() - 1) + "%')) ";
            }
        }

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, invoiceId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return count;
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
                for (Service service : services) {
                    if(service.getServiceId() == rs.getInt("serviceID")){
                        sc.setService(service);
                    }
                }
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

    // hàm này có vấn đề - KhangPM
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

    // chỉnh sửa - KhangPM
    public List<Service> unregisteredService2(int apartmentID, Date currentDate) {
        List<Service> list = new ArrayList<>();
        try {
            String sql = "SELECT s.*\n"
                    + "FROM Service s\n"
                    + "LEFT JOIN ServiceContract sc \n"
                    + "    ON s.serviceID = sc.serviceID \n"
                    + "    AND sc.apartmentID = ?\n"
                    + "    AND sc.endDate > ?\n"
                    + "WHERE sc.serviceContractID IS NULL";
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

    public int getRecentServiceContractId(int apartmentId) {
        int id = 0;
        String sql = "select top 1 serviceContractID from ServiceContract\n"
                + "where apartmentID = ?\n"
                + "order by serviceContractID desc";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
        return id;
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
//       sdao.insertServiceContract(7, 1, Date.valueOf("2004-07-08"), Date.valueOf("2004-03-12"), 1);
        
        List<ServiceContract> scList = sdao.serviceContractById(1, 11, 2024);
        System.out.println(scList.size());

        List<Service> serviceList = sdao.unregisteredService(1, Date.valueOf(LocalDate.now()));
        System.out.println("count: " +serviceList.size());
        
        ServiceContract sc = sdao.getServiceContractByInvoiceId(13);
        System.out.println(sc);
    }
}
