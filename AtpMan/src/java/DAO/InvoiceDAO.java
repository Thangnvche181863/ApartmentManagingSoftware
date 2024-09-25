/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.DBContext;
import model.*;

/**
 *
 * @author ADMIN
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
                invoice.setInvoiceID(rs.getInt(1));
                invoice.setApartmentID(rs.getInt(2));
                invoice.setAmount(rs.getDouble(3));
                invoice.setIssueDate(rs.getDate(4));
                invoice.setDueDate(rs.getDate(5));
                invoice.setStatus(rs.getInt(6));
                invoice.setTransactionDate(rs.getDate(7));
                list.add(invoice);
            }
        } catch (SQLException | ClassNotFoundException e) {
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
                //declare invoice object
                    //declare service object
                    Service service = new Service();

                    service.setServiceID(rs.getInt("serviceID"));
                    service.setName(rs.getString("name"));
                    service.setType(rs.getString("type"));
                    service.setDescription(rs.getString("description"));
                    service.setFee(rs.getDouble("fee"));

                    //declare serviceContract object
                    ServiceContract serviceContract = new ServiceContract();

                    serviceContract.setServiceContractID(rs.getInt("serviceContractID"));
                    serviceContract.setApartmentID(rs.getInt("apartmentID"));
                    serviceContract.setService(service);
                    serviceContract.setStartDate(rs.getDate("startDate"));
                    serviceContract.setEndDate(rs.getDate("endDate"));
                    serviceContract.setAmount(rs.getDouble("contractAmount"));
                    
                    serviceList.add(serviceContract);

                // set data for invoice
                invoice.setInvoiceID(rs.getInt(1));
                invoice.setApartmentID(rs.getInt(3));
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

    public List<Date> getAllApartmentInvoiceDate(int apartmentId){
        List<Date> list = new ArrayList<>();
        String sql = "Select issueDate from Invoice where apartmentID = ? ";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Date date = rs.getDate(1);
                list.add(date);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return list;
    }
    public List<Integer> getAllApartmentInvoiceYear(int apartmentId){
        List<Integer> list = new ArrayList<>();
        String sql = "Select distinct YEAR(issueDate) as year from Invoice where apartmentID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                int year = rs.getInt(1);
                list.add(year);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        InvoiceDAO dao = new InvoiceDAO();
        List<Invoice> list = dao.getAllInvoiceByApartmentID(1);
        List<Date> dList = dao.getAllApartmentInvoiceDate(1);
        List<Integer> yList = dao.getAllApartmentInvoiceYear(1);
        
        Invoice i = dao.getAllInvoiceByApartmentIDandMonth(1, 12, 2023);
//        System.out.println(i.getServiceContractList().get(0).getService().getName());
//        for (ServiceContract serviceContract : i.getServiceContractList()) {
//            System.out.println(serviceContract.getService().getName());
//        }
        
        System.out.println(i);
    }
}
