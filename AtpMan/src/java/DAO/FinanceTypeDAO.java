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
import model.FinanceType;
import utils.DBContext;
import java.sql.*;

/**
 *
 * @author thang
 */
public class FinanceTypeDAO {

    Connection connection = null;

    public List<FinanceType> getAll() {
        List<FinanceType> list = new ArrayList<>();
        Connection conn = null;
        try {
            String sql = "Select * from FinanceType";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FinanceType ft = new FinanceType();
                ft.setFinanceTypeId(rs.getInt(1));
                ft.setName(rs.getString(2));
                list.add(ft);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertFinanceType(String name) {
        try {
            String sql = "INSERT INTO [dbo].[FinanceType]\n"
                    + "           ([name])\n"
                    + "     VALUES\n"
                    + "           (?)";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteFinanceType(int financeTypeId) {
        Connection conn = null;
        try {
            String sql = "DELETE FROM [dbo].[FinanceType]\n"
                    + "      WHERE financeTypeId = ?";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, financeTypeId);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateFinaceType(int financeTypeId, String name) {
        try {
            String sql = "UPDATE [dbo].[FinanceType]\n"
                    + "   SET [name] = ?\n"
                    + " WHERE financeTypeId = ?";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, financeTypeId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        FinanceTypeDAO fdao = new FinanceTypeDAO();
        fdao.insertFinanceType("thu tiền bảo trì căn hộ");
//        fdao.deleteFinanceType(31);

//        fda
    }
}
