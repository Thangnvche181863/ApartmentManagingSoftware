/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
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
    
    public List<Invoice> getAllInvoiceByApartmentID(int apartmentID){
        List<Invoice> list = new ArrayList<>();
        String sql = "select * from Invoice where apartmentID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentID);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
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
    
    
    public static void main(String[] args) {
        InvoiceDAO dao = new InvoiceDAO();
        List<Invoice> list = dao.getAllInvoiceByApartmentID(2);
        
        System.out.println(list.size());
    }
}
