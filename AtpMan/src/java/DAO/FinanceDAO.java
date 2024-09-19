/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Finance;

/**
 *
 * @author thang
 */
public class FinanceDAO extends DBContext {

    public List<Finance> getAll() {
        List<Finance> list = new ArrayList<>();
        try {
            String sql = "Select * from Finance";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Finance(rs.getInt("financeID"), rs.getString("financeType"), rs.getDouble("amount"), rs.getDouble("incidentalCharges"), rs.getDouble("serviceFee"), rs.getString("providerName")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertFinance(String financeType, double amount, double incidentalCharges, double serviceFee, String providerName) {
        try {
            String sql = "Insert into Finance(financeType,amount,incidentalCharges,serviceFee,providerName) values(?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, financeType);
            ps.setDouble(2, amount);
            ps.setDouble(3, incidentalCharges);
            ps.setDouble(4, serviceFee);
            ps.setString(5, providerName);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteFinance(int financeId) {
        try {
            String sql = "DELETE FROM [dbo].[Finance]\n"
                    + "      WHERE financeId = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, financeId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateFinance(int financeId, String financeType, double amount, double incidentalCharges, double serviceFee, String providerName) {
        try {
            String sql = "UPDATE [dbo].[Finance]\n"
                    + "   SET [financeType] = ?\n"
                    + "      ,[amount] = ?\n"
                    + "      ,[incidentalCharges] = ?\n"
                    + "      ,[serviceFee] = ?\n"
                    + "      ,[providerName] = ?\n"
                    + " WHERE financeId = ?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, financeType);
            ps.setDouble(2, amount);
            ps.setDouble(3, incidentalCharges);
            ps.setDouble(4, serviceFee);
            ps.setString(5, providerName);
            ps.setInt(6, financeId);
            
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        FinanceDAO fdao = new FinanceDAO();
//        fdao.insertFinance("Tenant fee", 500.00, 25.00, 200.00, "Mr.Thang");
//        fdao.updateFinance(1, "Security fee", 350.00, 25.00, 100, "Mr.Thang");
        fdao.deleteFinance(1);
        System.out.println(fdao.getAll());
    }
}
