/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;
import utils.DBContext;

/**
 *
 * @author thang
 */
public class DiscountDAO {

    Connection connection = null;

    public List<Discount> getAll() {
        List<Discount> list = new ArrayList<>();
        String sql = "select * from Discount";
        try {
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Discount d = new Discount();
                d.setServiceId(rs.getInt(1));
                d.setOneMonth(rs.getInt(2));
                d.setTwoMonth(rs.getInt(3));
                d.setThreeMonth(rs.getInt(4));

                list.add(d);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Discount getDiscountById(int id) {
        Discount d = new Discount();
        String sql = "select * from Discount where serviceID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                d.setServiceId(rs.getInt(1));
                d.setOneMonth(rs.getInt(2));
                d.setTwoMonth(rs.getInt(3));
                d.setThreeMonth(rs.getInt(4));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return d;
    }

    public void updateDiscountById(int id, int oneMonth, int twoMonth, int threeMonth) {
        String sql = "UPDATE [dbo].[Discount]\n"
                + "   SET [oneMonth] = ?\n"
                + "      ,[twoMonth] = ?\n"
                + "      ,[threeMonth] = ?\n"
                + " WHERE serviceId = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, oneMonth);
            ps.setInt(2, twoMonth);
            ps.setInt(3, threeMonth);
            ps.setInt(4, id);
            
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insertDiscount(int oneMonth, int twoMonth, int threeMonth){
        String sql = "insert into Discount (oneMonth,twoMonth,threeMonth) values (?,?,?)";
        try {
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, oneMonth);
            ps.setInt(2, twoMonth);
            ps.setInt(3, threeMonth);
            
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void deleteDiscount(int id){
        String sql = "delete from discount where serviceID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        DiscountDAO ddao = new DiscountDAO();
//        ddao.updateDiscountById(1, 2, 2, 2);
        System.out.println(ddao.getDiscountById(1));
    }
}
