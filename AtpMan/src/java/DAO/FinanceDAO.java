/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import utils.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Finance;

/**
 *
 * @author thang
 */
public class FinanceDAO {

    public List<Finance> getAll() {
        Connection conn = null;
        List<Finance> list = new ArrayList<>();
        try {
            String sql = "Select * from Finance";
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Finance(rs.getInt("financeId"), rs.getInt("buildingId"), rs.getInt("financeTypeID"), rs.getDouble("amount"), rs.getInt("month")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertFinance(int buildingId, int financeTypeId, double amount, int month) {
        Connection conn = null;
        try {
            String sql = "Insert into Finance(buildingId,financeTypeId,amount,month) values(?,?,?,?)";
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, buildingId);
            ps.setInt(2, financeTypeId);
            ps.setDouble(3, amount);
            ps.setInt(4, month);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteFinance(int financeId) {
        Connection conn = null;
        try {
            String sql = "DELETE FROM [dbo].[Finance]\n"
                    + "      WHERE financeId = ?";
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, financeId);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateFinance(int financeId, int buildingId, int financeTypeId, double amount, int month) {
        Connection conn = null;
        try {
            String sql = "UPDATE [dbo].[Finance]\n"
                    + "   SET [buildingID] = ?\n"
                    + "      ,[financeTypeID] = ?\n"
                    + "      ,[amount] = ?\n"
                    + "      ,[month] = ?\n"
                    + " WHERE financeId = ?";
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, buildingId);
            ps.setInt(2, financeId);
            ps.setDouble(3, amount);
            ps.setInt(4, month);
            ps.setInt(5, financeId);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        FinanceDAO fdao = new FinanceDAO();
//        fdao.insertFinance("Tenant fee", 500.00, 25.00, 200.00, "Mr.Thang");
//        fdao.updateFinance(1, "Security fee", 350.00, 25.00, 100, "Mr.Thang");
//        fdao.deleteFinance(1,2);
        System.out.println(fdao.getAll());
    }
}
