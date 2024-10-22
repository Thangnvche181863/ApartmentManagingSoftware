/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBContext;

/**
 *
 * @author Admin
 */
public class RoleDAO {

    Connection connection = null;

    public List<Role> getAllRole()  {
        List<Role> list = new ArrayList<>();
        String sql = "select * from Role";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                int roleID = rs.getInt(1);
                String role_name = rs.getString(2);
                String roleAuthority = rs.getString(3);
                Role role = new Role(roleID, role_name, roleAuthority);
                list.add(role);
            }
        } catch (SQLException |ClassNotFoundException e) {
        }
        return list;
    }
    public static void main(String[] args) throws ClassNotFoundException {
        RoleDAO dao = new RoleDAO();
        List<Role> list = dao.getAllRole();
        System.out.println("" + list.toString());
    }
}
