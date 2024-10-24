/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import model.Invoice;
import utils.DBContext;
import model.*;

/**
 *
 * @author thang
 */
public class InvoiceDAO {

    Connection connection = null;

    public List<Invoice> getAllInvoiceByApartmentID(int apartmentID) {
        List<Invoice> list = new ArrayList<>();
        String sql = "select * from Invoice where apartmentID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt(1));
                invoice.setApartmentId(rs.getInt(2));
                invoice.setAmount(rs.getDouble(3));
                invoice.setIssueDate(rs.getDate(4));
                invoice.setDueDate(rs.getDate(5));
                invoice.setStatus(rs.getInt(6));
                invoice.setTransactionDate(rs.getDate(7));
                list.add(invoice);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return list;
    }

    /**
     *
     * @param apartmentID
     * @param month
     * @param year
     * @return
     */
    public List<ServiceContract> getAllServiceInvoiceByApartmentIDandMonth(int apartmentID, int month, int year) {
        List<ServiceContract> serviceList = new ArrayList<>();
        String sql = "select i.apartmentID, ins.serviceContractID, sc.serviceID, sc.startDate, sc.endDate, sc.amount as contractAmount, s.name, s.type, s.description, s.fee from InvoiceService ins\n"
                + "inner join Invoice i on ins.invoiceID = i.invoiceID\n"
                + "inner join ServiceContract sc on ins.serviceContractID = sc.serviceContractID\n"
                + "inner join Service s on sc.serviceID = s.serviceID\n"
                + "where i.apartmentID = ? and MONTH(i.issueDate) = ? and YEAR(i.issueDate) = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentID);
            statement.setInt(2, month);
            statement.setInt(3, year);
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
                serviceContract.setAmount(rs.getDouble("contractAmount"));

                serviceList.add(serviceContract);

            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return serviceList;
    }

    public Invoice getAllInvoiceByApartmentIDandMonth(int apartmentID, int month, int year) {
        Invoice invoice = new Invoice();
        List<ServiceContract> serviceList = new ArrayList<>();
        String sql = "select ins.invoiceID, ins.serviceContractID, i.apartmentID, i.amount, i.issueDate, i.dueDate, i.status, i.transactionDate, sc.serviceID, sc.startDate, sc.endDate, sc.amount as contractAmount, s.name, s.type, s.description, s.fee from InvoiceService ins\n"
                + "inner join Invoice i on ins.invoiceID = i.invoiceID\n"
                + "inner join ServiceContract sc on ins.serviceContractID = sc.serviceContractID\n"
                + "inner join Service s on sc.serviceID = s.serviceID\n"
                + "where i.apartmentID = ? and MONTH(i.issueDate) = ? and YEAR(i.issueDate) = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentID);
            statement.setInt(2, month);
            statement.setInt(3, year);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // declare invoice object
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
                serviceContract.setAmount(rs.getBigDecimal("contractAmount"));

                serviceList.add(serviceContract);

                // set data for invoice
                invoice.setInvoiceId(rs.getInt(1));
                invoice.setApartmentId(rs.getInt(3));
                invoice.setAmount(rs.getDouble(4));
                invoice.setIssueDate(rs.getDate(5));
                invoice.setDueDate(rs.getDate(6));
                invoice.setStatus(rs.getInt(7));
                invoice.setTransactionDate(rs.getDate(8));
                invoice.setServiceContractList(serviceList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return invoice;
    }

    public Invoice getInvoiceByApartmentIDandMonth(int apartmentID, int month, int year, int page, int rowPerPage, List<String> searchTermList) {
        Invoice invoice = new Invoice();
        List<ServiceContract> serviceList = new ArrayList<>();
        String sql = "select ins.invoiceID, ins.serviceContractID, i.apartmentID, i.amount, i.issueDate, i.dueDate, i.status, i.transactionDate, sc.serviceID, sc.startDate, sc.endDate, sc.amount as contractAmount, s.name, s.type, s.description, s.fee from InvoiceService ins\n"
                + "inner join Invoice i on ins.invoiceID = i.invoiceID\n"
                + "inner join ServiceContract sc on ins.serviceContractID = sc.serviceContractID\n"
                + "inner join Service s on sc.serviceID = s.serviceID\n"
                + "where i.apartmentID = ? and MONTH(i.issueDate) = ? and YEAR(i.issueDate) = ?";

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
        sql += " order by ins.invoiceID offset ? rows fetch next ? rows only";

        int fetchStart = (page - 1) * rowPerPage;

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentID);
            statement.setInt(2, month);
            statement.setInt(3, year);
            statement.setInt(4, fetchStart);
            statement.setInt(5, rowPerPage);

            ResultSet rs = statement.executeQuery();
            LinkedHashMap<Integer, Invoice> map = new LinkedHashMap<>();
            while (rs.next()) {
                int invoiceID = rs.getInt(1);
                invoice = map.get(invoiceID);

                if (invoice == null) {
                    invoice = new Invoice();
                    invoice.setInvoiceId(invoiceID);
                    invoice.setApartmentId(rs.getInt(3));
                    invoice.setAmount(rs.getDouble(4));
                    invoice.setIssueDate(rs.getDate(5));
                    invoice.setDueDate(rs.getDate(6));
                    invoice.setStatus(rs.getInt(7));
                    invoice.setTransactionDate(rs.getDate(8));
                    map.put(invoiceID, invoice);
                }

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
                serviceContract.setAmount(rs.getDouble("contractAmount"));

                serviceList.add(serviceContract);

                // set data for invoice
                invoice.setServiceContractList(serviceList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return invoice;
    }

    public int countInvoiceByApartmentIDandMonth(int apartmentID, int month, int year, List<String> searchTermList) {
        int count = 0;
        String sql = "select count(*) from InvoiceService ins\n"
                + "inner join Invoice i on ins.invoiceID = i.invoiceID\n"
                + "inner join ServiceContract sc on ins.serviceContractID = sc.serviceContractID\n"
                + "inner join Service s on sc.serviceID = s.serviceID\n"
                + "where i.apartmentID = ? and MONTH(i.issueDate) = ? and YEAR(i.issueDate) = ?";
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
            statement.setInt(1, apartmentID);
            statement.setInt(2, month);
            statement.setInt(3, year);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return count;
    }

    public List<Date> getAllApartmentInvoiceDate(int apartmentId) {
        List<Date> list = new ArrayList<>();
        String sql = "Select issueDate from Invoice where apartmentID = ? ";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate(1);
                list.add(date);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Integer> getAllApartmentInvoiceYear(int apartmentId) {
        List<Integer> list = new ArrayList<>();
        String sql = "Select distinct YEAR(issueDate) as year from Invoice where apartmentID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int year = rs.getInt(1);
                list.add(year);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return list;
    }

    // public void insertInvoice(int apartmentId, double amount, Date issueDate, Date dueDate, int status,
    //         Date transactionDate) {
    //     try {
    //         String sql = "insert into Invoice(apartmentId,amount,issueDate,dueDate,status,transactionDate) values(?,?,?,?,?,?)";
    //         PreparedStatement ps = connection.prepareStatement(sql);
    //         ps.setInt(1, apartmentId);
    //         ps.setDouble(2, amount);
    //         ps.setDate(3, issueDate);
    //         ps.setDate(4, dueDate);
    //         ps.setInt(5, status);
    //         ps.setDate(6, transactionDate);
    //         ps.executeUpdate();
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }
    // }
    // public void deleteInvoice(int invoiceId) {
    //     try {
    //         String sql = "DELETE FROM [dbo].[Invoice]\n"
    //                 + "      WHERE invoiceId = ?";
    //         PreparedStatement ps = connection.prepareStatement(sql);
    //         ps.setInt(1, invoiceId);
    //         ps.executeUpdate();
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }
    // }
    // public void updateInvoice(int invoiceId, int apartmentId, double amount, Date issueDate, Date dueDate, int status,
    //         Date transactionDate) {
    //     try {
    //         String sql = "UPDATE [dbo].[Invoice]\n"
    //                 + "   SET [apartmentID] = ?\n"
    //                 + "      ,[amount] = ?\n"
    //                 + "      ,[issueDate] = ?\n"
    //                 + "      ,[dueDate] = ?\n"
    //                 + "      ,[status] = ?\n"
    //                 + "      ,[transactionDate] = ?\n"
    //                 + " WHERE invoiceId = ?";
    //         PreparedStatement ps = connection.prepareStatement(sql);
    //         ps.setInt(1, apartmentId);
    //         ps.setDouble(2, amount);
    //         ps.setDate(3, issueDate);
    //         ps.setDate(4, dueDate);
    //         ps.setInt(5, status);
    //         ps.setDate(6, transactionDate);
    //         ps.setInt(7, invoiceId);
    //         ps.executeUpdate();
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }
    // }
    public static void main(String[] args) {
        InvoiceDAO dao = new InvoiceDAO();
        List<Invoice> list = dao.getAllInvoiceByApartmentID(1);
        List<Date> dList = dao.getAllApartmentInvoiceDate(1);
        List<Integer> yList = dao.getAllApartmentInvoiceYear(1);

        List<String> sList = new ArrayList<>();
        sList.add("service");
        sList.add("b");
        
        List<ServiceContract> serviceContractsList = dao.getAllServiceInvoiceByApartmentIDandMonth(1, 11, 2024);
        Invoice i = dao.getInvoiceByApartmentIDandMonth(1, 11, 2024, 1, 5, null);
        // System.out.println(i.getServiceContractList().get(0).getService().getName());
        // for (ServiceContract serviceContract : i.getServiceContractList()) {
        // System.out.println(serviceContract.getService().getName());
        // }

        int count = dao.countInvoiceByApartmentIDandMonth(1, 9, 2024, null);
        System.out.println(serviceContractsList.size());
    }
}
