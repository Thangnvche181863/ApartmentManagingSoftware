/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.InvoiceService;

/**
 *
 * @author thang
 */
public class InvoiceServiceDAO extends DBContext {

    public List<InvoiceService> getAll() {
        List<InvoiceService> list = new ArrayList<>();

        try {
            String sql = "Select * from InvoiceService";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new InvoiceService(rs.getInt(1), rs.getInt(2)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertInvoiceService(int invoiceId, int serviceContractId) {
        try {
            String sql = "INSERT INTO [dbo].[InvoiceService]\n"
                    + "           ([invoiceID]\n"
                    + "           ,[serviceContractID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, invoiceId);
            ps.setInt(2, serviceContractId);
            
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        InvoiceServiceDAO idao = new InvoiceServiceDAO();
        
    }
}
