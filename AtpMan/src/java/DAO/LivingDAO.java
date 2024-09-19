/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import utils.DBContext;
import model.*;

/**
 *
 * @author ADMIN
 */
public class LivingDAO {

    public List<Living> getAllResident() {
        Connection connection = null;
        List<Living> list = new ArrayList<>();
        String sql = "select * from Living";
        try {
            connection = DBContext.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Living living = new Living();
                    living.setLivingID(rs.getInt(1));
                    living.setCustomerID(rs.getInt(2));
                    living.setApartmentID(rs.getInt(3));
                    living.setStartDate(rs.getDate(4));
                    living.setEndDate(rs.getDate(5));
                    list.add(living);
                }
            }
        } catch (SQLException | ClassNotFoundException ex){
            
        }
        return list;
        }
    
    

    public static void main(String[] args) {
        LivingDAO dao = new LivingDAO();
        List<Living> list = dao.getAllResident();
        System.out.println(list.size());
    }
}
