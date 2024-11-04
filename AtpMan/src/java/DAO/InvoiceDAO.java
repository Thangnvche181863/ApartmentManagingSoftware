/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
                invoice.setTransactionDate(rs.getTimestamp(7));
                invoice.setInvoiceCode(rs.getString(8));
                invoice.setTransactionNo(rs.getString(9));
                invoice.setBankCode(rs.getString(10));
                invoice.setOrderInfo(rs.getString(11));
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
                serviceContract.setAmount(rs.getBigDecimal("contractAmount"));

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
                invoice.setTransactionDate(rs.getTimestamp(8));
                invoice.setServiceContractList(serviceList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return invoice;
    }

    public Invoice getInvoiceByApartmentIDandMonth(int apartmentID, int month, int year, int page, int rowPerPage,
            List<String> searchTermList) {
        Invoice invoice = new Invoice();
        List<ServiceContract> serviceList = new ArrayList<>();
        String sql = "select ins.invoiceID, ins.serviceContractID, i.apartmentID, i.amount, i.issueDate,\n"
                + "i.dueDate, i.status, i.transactionDate, i.invoiceCode, i.transactionNo, i.bankCode, i.orderInfo,\n"
                + "sc.serviceID, sc.startDate, sc.endDate, sc.amount as contractAmount, s.name, s.type, s.description, s.fee from InvoiceService ins\n"
                + "inner join Invoice i on ins.invoiceID = i.invoiceID\n"
                + "inner join ServiceContract sc on ins.serviceContractID = sc.serviceContractID\n"
                + "inner join Service s on sc.serviceID = s.serviceID\n"
                + "where i.apartmentID = ? and MONTH(i.issueDate) = ? and YEAR(i.issueDate) = ?";

        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() == 1) {
                // and ((s.name like N'%%' or s.type like N'%%')) in SQL
                sql += " and (s.name like N'%" + searchTermList.get(0) + "%' or s.type like N'%" + searchTermList.get(0)
                        + "%')";
            } else {
                // and ((s.name like N'%%' and s.name like N'%%') or (s.type like N'%%' and
                // s.type like N'%%')) in SQL
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
                int invoiceID = rs.getInt("invoiceID");
                invoice = map.get(invoiceID);

                if (invoice == null) {
                    invoice = new Invoice();
                    invoice.setInvoiceId(invoiceID);
                    invoice.setApartmentId(rs.getInt("apartmentID"));
                    invoice.setAmount(rs.getDouble("amount"));
                    invoice.setIssueDate(rs.getDate("issueDate"));
                    invoice.setDueDate(rs.getDate("dueDate"));
                    invoice.setStatus(rs.getInt("status"));
                    invoice.setTransactionDate(rs.getTimestamp("transactionDate"));
                    // invoice.setInvoiceCode(rs.getString("invoiceCode"));
                    // invoice.setTransactionNo(rs.getString("transactionNo"));
                    // invoice.setBankCode(rs.getString("bankCode"));
                    // invoice.setOrderInfo(rs.getString("orderInfo"));
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
                serviceContract.setAmount(rs.getBigDecimal("contractAmount"));

                serviceList.add(serviceContract);

                // set data for invoice
                invoice.setServiceContractList(serviceList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return invoice;
    }

    public Invoice getInvoiceByApartmentIDandMonthYear(int apartmentID, int month, int year) {
        Invoice invoice = null;
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
                    invoice.setTransactionDate(rs.getTimestamp(8));
                    map.put(invoiceID, invoice);
                } else {
                    return invoice;
                }
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
                // and ((s.name like N'%%' or s.type like N'%%')) in SQL
                sql += " and (s.name like N'%" + searchTermList.get(0) + "%' or s.type like N'%" + searchTermList.get(0)
                        + "%')";
            } else {
                // and ((s.name like N'%%' and s.name like N'%%') or (s.type like N'%%' and
                // s.type like N'%%')) in SQL
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

    // KhangPM
    public int updateInvoiceTransaction(int invoiceId, Timestamp transactionDate, String invoiceCode,
            String transactionNo, String bankCode, String orderInfo) {
        int i = 0;
        String sql = "update Invoice\n"
                + "set status = 1, transactionDate =  ?, invoiceCode = ?, transactionNo = ?, bankCode = ?, orderInfo = ?\n"
                + "where invoiceID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, transactionDate);
            statement.setString(2, invoiceCode);
            statement.setString(3, transactionNo);
            statement.setString(4, bankCode);
            statement.setString(5, orderInfo);
            statement.setInt(6, invoiceId);
            i = statement.executeUpdate();
            return i;
        } catch (SQLException | ClassNotFoundException e) {
        }
        return i;
    }

    public BigDecimal totalPaidInvoice() {
        double total = 0;
        String sql = "select sum(amount) from Invoice\n"
                + "where status = 1";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return BigDecimal.valueOf(total);
    }

    public int totalInvoiceByStatus(int status) {
        int total = 0;
        String sql = "select count(*) from Invoice\n"
                + "where status = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, status);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return total;
    }

    // KhangPM
    public Date getEarliestDateInvoice() {
        Date date = null;
        String sql = "SELECT MIN(CONVERT(DATE, transactionDate )) AS transactionDate from Invoice\n"
                + "where status = 1";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                date = rs.getDate(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return date;
    }

    // KhangPM
    public Date getEarliestIssueDateInvoice() {
        Date date = null;
        String sql = "SELECT MIN(CONVERT(DATE, issueDate )) AS issueDate from Invoice\n"
                + "where status = 0";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                date = rs.getDate(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return date;
    }

    // KhangPM
    public List<Invoice> getInvoiceForStaff(int status, int currentPage, int rowPerPage, Date fromDate, Date toDate,
            String invoiceCode, String transactionNo, String orderInfo) {
        List<Invoice> list = new ArrayList<>();
        String sql = "select i.*, a.apartmentNumber from Invoice i "
                + "inner join Apartment a on a.apartmentID = i.apartmentID "
                + "where status = ? ";

        // Tạo truy vấn động theo điều kiện có giá trị khác null
        if (status == 1) {
            if (toDate != null) {
                sql += " and CONVERT(DATE, i.transactionDate) <= ? ";
            }
            if (fromDate != null) {
                sql += " and ? <= CONVERT(DATE, i.transactionDate) ";
            }
            if (invoiceCode != null && !invoiceCode.isBlank()) {
                sql += " and i.invoiceCode = ? ";
            }
            if (transactionNo != null && !transactionNo.isBlank()) {
                sql += " and i.transactionNo = ? ";
            }
            if (orderInfo != null && !orderInfo.isBlank()) {
                sql += " and i.orderInfo like ? ";
            }
            sql += "order by i.transactionDate desc offset ? rows fetch next ? rows only";
        } else {
            if (toDate != null) {
                sql += " and CONVERT(DATE, i.issueDate) <= ? ";
            }
            if (fromDate != null) {
                sql += " and ? <= CONVERT(DATE, i.issueDate) ";
            }
            sql += "order by i.issueDate desc offset ? rows fetch next ? rows only";
        }

        int fetchNext = (currentPage - 1) * rowPerPage;

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int index = 1;

            // Gán giá trị cho các tham số trong thứ tự tương ứng với truy vấn SQL
            statement.setInt(index++, status);

            if (status == 1) {
                if (toDate != null) {
                    statement.setDate(index++, toDate);
                }
                if (fromDate != null) {
                    statement.setDate(index++, fromDate);
                }
                if (invoiceCode != null && !invoiceCode.isBlank()) {
                    statement.setString(index++, invoiceCode.trim());
                }
                if (transactionNo != null && !transactionNo.isBlank()) {
                    statement.setString(index++, transactionNo.trim());
                }
                if (orderInfo != null && !orderInfo.isBlank()) {
                    statement.setString(index++, "%" + orderInfo.trim() + "%");
                }
            } else {
                if (toDate != null) {
                    statement.setDate(index++, toDate);
                }
                if (fromDate != null) {
                    statement.setDate(index++, fromDate);
                }
            }

            // Phân trang
            statement.setInt(index++, fetchNext);
            statement.setInt(index, rowPerPage);

            ResultSet rs = statement.executeQuery();

            // Xử lý dữ liệu từ kết quả truy vấn
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt(1));
                invoice.setApartmentId(rs.getInt(2));
                invoice.setAmount(rs.getDouble(3));
                invoice.setIssueDate(rs.getDate(4));
                invoice.setDueDate(rs.getDate(5));
                invoice.setStatus(rs.getInt(6));
                invoice.setTransactionDate(rs.getTimestamp(7));
                invoice.setInvoiceCode(rs.getString(8));
                invoice.setTransactionNo(rs.getString(9));
                invoice.setBankCode(rs.getString(10));
                invoice.setOrderInfo(rs.getString(11));
                invoice.setApartmentName(rs.getString(12));
                list.add(invoice);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("err: " + e.getMessage());
        }
        return list;
    }

    // KhangPM
    public int countInvoiceForStaff(int status, Date fromDate, Date toDate, String invoiceCode, String transactionNo,
            String orderInfo) {
        int count = 0;
        String sql = "select count(*) from Invoice i "
                + "inner join Apartment a on a.apartmentID = i.apartmentID "
                + "where status = ? ";

        // Tạo truy vấn động theo điều kiện có giá trị khác null
        if (status == 1) {
            if (toDate != null) {
                sql += " and CONVERT(DATE, i.transactionDate) <= ? ";
            }
            if (fromDate != null) {
                sql += " and ? <= CONVERT(DATE, i.transactionDate) ";
            }
            if (invoiceCode != null && !invoiceCode.isBlank()) {
                sql += " and i.invoiceCode = ? ";
            }
            if (transactionNo != null && !transactionNo.isBlank()) {
                sql += " and i.transactionNo = ? ";
            }
            if (orderInfo != null && !orderInfo.isBlank()) {
                sql += " and i.orderInfo like ? ";
            }
        } else {
            if (toDate != null) {
                sql += " and CONVERT(DATE, i.issueDate) <= ? ";
            }
            if (fromDate != null) {
                sql += " and ? <= CONVERT(DATE, i.issueDate) ";
            }
        }

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int index = 1;

            // Gán giá trị cho các tham số trong thứ tự tương ứng với truy vấn SQL
            statement.setInt(index++, status);

            if (status == 1) {
                if (toDate != null) {
                    statement.setDate(index++, toDate);
                }
                if (fromDate != null) {
                    statement.setDate(index++, fromDate);
                }
                if (invoiceCode != null && !invoiceCode.isBlank()) {
                    statement.setString(index++, invoiceCode.trim());
                }
                if (transactionNo != null && !transactionNo.isBlank()) {
                    statement.setString(index++, transactionNo.trim());
                }
                if (orderInfo != null && !orderInfo.isBlank()) {
                    statement.setString(index++, "%" + orderInfo.trim() + "%");
                }
            } else {
                if (toDate != null) {
                    statement.setDate(index++, toDate);
                }
                if (fromDate != null) {
                    statement.setDate(index++, fromDate);
                }
            }

            ResultSet rs = statement.executeQuery();

            // Xử lý dữ liệu từ kết quả truy vấn
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("err: " + e.getMessage());
        }
        return count;
    }

    // KhangPM
    public double totalAmountPaidInvoice(int status, Date fromDate, Date toDate, String invoiceCode,
            String transactionNo, String orderInfo) {
        double total = 0;
        String sql = "select SUM(i.amount) from Invoice i "
                + "inner join Apartment a on a.apartmentID = i.apartmentID "
                + "where status = ? ";

        // Tạo truy vấn động theo điều kiện có giá trị khác null
        if (status == 1) {
            if (toDate != null) {
                sql += " and CONVERT(DATE, i.transactionDate) <= ? ";
            }
            if (fromDate != null) {
                sql += " and ? <= CONVERT(DATE, i.transactionDate) ";
            }
            if (invoiceCode != null && !invoiceCode.isBlank()) {
                sql += " and i.invoiceCode = ? ";
            }
            if (transactionNo != null && !transactionNo.isBlank()) {
                sql += " and i.transactionNo = ? ";
            }
            if (orderInfo != null && !orderInfo.isBlank()) {
                sql += " and i.orderInfo like ? ";
            }
        } else {
            if (toDate != null) {
                sql += " and CONVERT(DATE, i.issueDate) <= ? ";
            }
            if (fromDate != null) {
                sql += " and ? <= CONVERT(DATE, i.issueDate) ";
            }
        }

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int index = 1;

            // Gán giá trị cho các tham số trong thứ tự tương ứng với truy vấn SQL
            statement.setInt(index++, status);

            if (status == 1) {
                if (toDate != null) {
                    statement.setDate(index++, toDate);
                }
                if (fromDate != null) {
                    statement.setDate(index++, fromDate);
                }
                if (invoiceCode != null && !invoiceCode.isBlank()) {
                    statement.setString(index++, invoiceCode.trim());
                }
                if (transactionNo != null && !transactionNo.isBlank()) {
                    statement.setString(index++, transactionNo.trim());
                }
                if (orderInfo != null && !orderInfo.isBlank()) {
                    statement.setString(index++, "%" + orderInfo.trim() + "%");
                }
            } else {
                if (toDate != null) {
                    statement.setDate(index++, toDate);
                }
                if (fromDate != null) {
                    statement.setDate(index++, fromDate);
                }
            }

            ResultSet rs = statement.executeQuery();

            // Xử lý dữ liệu từ kết quả truy vấn
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("err: " + e.getMessage());
        }
        return total;
    }

    // KhangPM
    public Map<String, Double> getAmountByInvoiceId(int invoiceId) {
        Map<String, Double> map = new LinkedHashMap<>();
        double amount = 1;
        String invoiceCode = "";
        String sql = "select invoiceCode, amount from Invoice\n"
                + "where invoiceID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, invoiceId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                invoiceCode = rs.getString(1);
                amount = rs.getDouble(2);

                map.put(invoiceCode, amount);
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
        return map;
    }

    // public void insertInvoice(int apartmentId, double amount, Date issueDate,
    // Date dueDate, int status,
    // Date transactionDate) {
    // try {
    // String sql = "insert into
    // Invoice(apartmentId,amount,issueDate,dueDate,status,transactionDate)
    // values(?,?,?,?,?,?)";
    // PreparedStatement ps = connection.prepareStatement(sql);
    // ps.setInt(1, apartmentId);
    // ps.setDouble(2, amount);
    // ps.setDate(3, issueDate);
    // ps.setDate(4, dueDate);
    // ps.setInt(5, status);
    // ps.setDate(6, transactionDate);
    // ps.executeUpdate();
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }
    // public void deleteInvoice(int invoiceId) {
    // try {
    // String sql = "DELETE FROM [dbo].[Invoice]\n"
    // + " WHERE invoiceId = ?";
    // PreparedStatement ps = connection.prepareStatement(sql);
    // ps.setInt(1, invoiceId);
    // ps.executeUpdate();
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }

    // public void updateInvoice(int invoiceId, int apartmentId, double amount, Date
    // issueDate, Date dueDate, int status,
    // Date transactionDate) {
    // try {
    // String sql = "UPDATE [dbo].[Invoice]\n"
    // + " SET [apartmentID] = ?\n"
    // + " ,[amount] = ?\n"
    // + " ,[issueDate] = ?\n"
    // + " ,[dueDate] = ?\n"
    // + " ,[status] = ?\n"
    // + " ,[transactionDate] = ?\n"
    // + " WHERE invoiceId = ?";
    // PreparedStatement ps = connection.prepareStatement(sql);
    // ps.setInt(1, apartmentId);
    // ps.setDouble(2, amount);
    // ps.setDate(3, issueDate);
    // ps.setDate(4, dueDate);
    // ps.setInt(5, status);
    // ps.setDate(6, transactionDate);
    // ps.setInt(7, invoiceId);
    // ps.executeUpdate();
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }
    // public Statistic statisticInvoice(int year) {
    // Statistic s = new Statistic();
    // String sql = "SELECT \n"
    // + " COUNT(*) AS tong_hoa_don, \n"
    // + " SUM(amount) AS tong_so_tien,\n"
    // + " COUNT(CASE WHEN status = 0 THEN 1 END) AS
    // tong_hoa_don_chua_thanh_toan,\n"
    // + " SUM(CASE WHEN status = 0 THEN amount ELSE 0 END) AS
    // tong_so_tien_chua_thanh_toan\n"
    // + "FROM \n"
    // + " Invoice\n"
    // + "WHERE \n"
    // + " YEAR(issueDate) = ?";
    // try {
    // connection = DBContext.getConnection();
    // PreparedStatement ps = connection.prepareStatement(sql);
    // ps.setInt(1, year);
    // ResultSet rs = ps.executeQuery();
    // while (rs.next()) {
    // s.setTotalBill(rs.getInt(1));
    // s.setTotalAmount(rs.getBigDecimal(2));
    // s.setTotalUnBill(rs.getInt(3));
    // s.setTotalUnPay(rs.getBigDecimal(4));
    // }
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // return s;
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

        System.out.println(i);

        LocalDate fromDate = LocalDate.of(2024, 10, 15);
        LocalDate toDate = LocalDate.of(2024, 3, 31);

        System.out.println(Date.valueOf(fromDate));

        List<Invoice> iList = dao.getInvoiceForStaff(0, 1, 10, null, Date.valueOf(toDate), "25742162", "14642203",
                null);
        System.out.println("invoiceList size: " + iList.size());

        int count = dao.countInvoiceForStaff(1, Date.valueOf(fromDate), Date.valueOf(toDate), "  ", "  ", null);
        System.out.println(count);
    }
}
