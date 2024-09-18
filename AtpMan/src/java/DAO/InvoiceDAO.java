/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Invoice;

/**
 *
 * @author thang
 */
public class InvoiceDAO extends DBContext {

    public List<Invoice> getAll() {

        List<Invoice> list = new ArrayList<>();

        try {
            String sql = "Select * from Invoice";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Invoice(rs.getInt("invoiceID"), rs.getInt("apartmentID"), rs.getDouble("amount"), rs.getDate("issueDate"), rs.getDate("dueDate"), rs.getInt("status"), rs.getDate("transactionDate")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertInvoice(int apartmentId, double amount, Date issueDate, Date dueDate, int status, Date transactionDate) {
        try {
            String sql = "insert into Invoice(apartmentId,amount,issueDate,dueDate,status,transactionDate) values(?,?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, apartmentId);
            ps.setDouble(2, amount);
            ps.setDate(3, issueDate);
            ps.setDate(4, dueDate);
            ps.setInt(5, status);
            ps.setDate(6, transactionDate);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteInvoice(int invoiceId) {
        try {
            String sql = "DELETE FROM [dbo].[Invoice]\n"
                    + "      WHERE invoiceId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, invoiceId);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateInvoice(int invoiceId,int apartmentId, double amount, Date issueDate, Date dueDate, int status, Date transactionDate) {
        try {
            String sql = "UPDATE [dbo].[Invoice]\n"
                    + "   SET [apartmentID] = ?\n"
                    + "      ,[amount] = ?\n"
                    + "      ,[issueDate] = ?\n"
                    + "      ,[dueDate] = ?\n"
                    + "      ,[status] = ?\n"
                    + "      ,[transactionDate] = ?\n"
                    + " WHERE invoiceId = ?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, apartmentId);
            ps.setDouble(2, amount);
            ps.setDate(3, issueDate);
            ps.setDate(4, dueDate);
            ps.setInt(5, status);
            ps.setDate(6, transactionDate);
            ps.setInt(7, invoiceId);
            
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        InvoiceDAO idao = new InvoiceDAO();
//        idao.insertInvoice(1, 1, Date.valueOf("2004-07-08"),Date.valueOf("2004-12-12"), 0,Date.valueOf("2004-03-12"));
//        idao.deleteInvoice(3);
//        idao.updateInvoice(3, 1, 2, Date.valueOf("2004-03-12"), Date.valueOf("2004-07-08"), 1, Date.valueOf("2004-07-22"));
        System.out.println(idao.getAll());
    }
}
