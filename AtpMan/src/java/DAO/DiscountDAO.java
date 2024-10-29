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

    public Discount getDiscountById(int id){
        Discount d = new Discount();
        String sql = "select * from Discount where discountID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
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
}
