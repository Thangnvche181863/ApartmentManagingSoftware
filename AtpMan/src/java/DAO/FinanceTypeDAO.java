/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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

        try {
            String sql = "Select * from FinanceBuilding";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new FinanceType(rs.getInt("financeTypeId"), rs.getString("name"), rs.getString("description")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertFinanceType(String name, String description) {
        try {
            String sql = "Insert into FinanceType (name,description) values(?,?)";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteFinanceType(int financeTypeId) {
        try {
            String sql = "ELETE FROM [dbo].[FinanceType]\n"
                    + "      WHERE financeTypeId = ?";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, financeTypeId);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateFinaceType(int financeTypeId, String name, String description) {
        try {
            String sql = "UPDATE [dbo].[FinanceType]\n"
                    + "   SET [name] = ?\n"
                    + "      ,[description] = ?\n"
                    + " WHERE financeTypeId = ?";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, financeTypeId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        FinanceTypeDAO fdao = new FinanceTypeDAO();
//        fdao.insertFinanceBuilding(1, 2);
//        fdao.deleteFinanceBuilding(1, 1);
        System.out.println(fdao.getAll());
    }
}
