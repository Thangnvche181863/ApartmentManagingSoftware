/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Finance;
import java.sql.*;
import java.time.LocalDate;
import utils.DBContext;

/**
 *
 * @author thang
 */
public class FinanceDAO {

    Connection connection = null;

    public List<Finance> getAll() {
        List<Finance> list = new ArrayList<>();
        try {
            String sql = "select f.financeID, b.name, ft.name,f.amount,f.date,f.description\n"
                    + "from finance f \n"
                    + "join FinanceType ft on ft.financeTypeID = f.financeTypeID\n"
                    + "join Building b on b.buildingID = f.buildingID";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Finance f = new Finance();
                f.setFinanceId(rs.getInt(1));
                f.setBuilding(rs.getString(2));
                f.setName(rs.getString(3));
                f.setAmount(rs.getBigDecimal(4));
                f.setDate(rs.getDate(5));
                f.setDescription(rs.getString(6));
                list.add(f);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Finance> getAllByTime(int year, int month, int buildingId) {
        List<Finance> list = new ArrayList<>();
        String sql = "select f.financeID, b.name, ft.name,f.amount,f.date,f.description\n"
                + "from finance f \n"
                + "join FinanceType ft on ft.financeTypeID = f.financeTypeID\n"
                + "join Building b on b.buildingID = f.buildingID\n"
                + "where year(date) = ? and month(date) = ? and b.buildingID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, year);
            ps.setInt(2, month);
            ps.setInt(3, buildingId);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Finance f = new Finance();
                f.setFinanceId(rs.getInt(1));
                f.setBuilding(rs.getString(2));
                f.setName(rs.getString(3));
                f.setAmount(rs.getBigDecimal(4));
                f.setDate(rs.getDate(5));
                f.setDescription(rs.getString(6));
                list.add(f);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertFinance(int buildingId, int financeTypeId, BigDecimal amount, Date date, String description) {
        try {
            String sql = "Insert into Finance(buildingId,financeTypeId,amount,date,description) values(?,?,?,?,?)";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, buildingId);
            ps.setInt(2, financeTypeId);
            ps.setBigDecimal(3, amount);
            ps.setDate(4, date);
            ps.setString(5, description);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteFinance(int financeId) {
        try {
            String sql = "DELETE FROM [dbo].[Finance]\n"
                    + "      WHERE financeId = ?";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, financeId);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateFinance(int financeId, int buildingId, int financeTypeId, BigDecimal amount, Date date, String description) {
        try {
            String sql = "UPDATE [dbo].[Finance]\n"
                    + "   SET [buildingID] = ?\n"
                    + "      ,[financeTypeID] = ?\n"
                    + "      ,[amount] = ?\n"
                    + "      ,[month] = ?\n"
                    + " WHERE financeId = ?";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, buildingId);
            ps.setInt(2, financeId);
            ps.setBigDecimal(3, amount);
            ps.setDate(4, date);
            ps.setString(5, description);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Finance getFinanceById(int id) {
        Finance f = new Finance();
        String sql = "select * from Finance where financeID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                f.setDescription(rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return f;
    }

    public static void main(String[] args) {
        FinanceDAO fdao = new FinanceDAO();
//        fdao.insertFinance(1, 4, BigDecimal.valueOf(2024), Date.valueOf("2024-10-9"), "hahaha");
//        fdao.updateFinance(1, "Security fee", 350.00, 25.00, 100, "Mr.Thang");
//        fdao.deleteFinance(2);
//        Date date = (Date) fdao.getAll().get(0).getDate();
//                int d = date.getDate() ;
//        System.out.println(d);
//System.out.println(fdao.getAll().get(0).getDate());
        System.out.println(fdao.getAll());

    }
}
