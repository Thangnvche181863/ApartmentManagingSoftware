/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author ADMIN
 */
public class OwnershipDAO {

    public List<Ownership> getAllOwner() {
        Connection connection = null;
        List<Ownership> list = new ArrayList<>();
        String sql = "select * from Ownership";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Ownership ownership = new Ownership();
                ownership.setOwnershipID(rs.getInt(1));
                ownership.setApartmentID(rs.getInt(2));
                ownership.setCustomerID(rs.getInt(3));
                ownership.setContractDate(rs.getDate(4));
                list.add(ownership);
            }
        } catch (SQLException | ClassNotFoundException e) {

        }
        return list;
    }
    
    public static void main(String[] args) {
        OwnershipDAO dao = new OwnershipDAO();
        List<Ownership> list = dao.getAllOwner();
        System.out.println(list.size());
    }
}
