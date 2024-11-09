/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;
import utils.DBContext;

/**
 *
 * @author WuanTun
 */
public class RoleDAO {

    private static final Logger LOGGER = Logger.getLogger(RoleDAO.class.getName());

    public List<Role> getRoles() {
        Connection conn = null;
        List<Role> roles = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Failed to connect to the database.");
            }

            if (conn != null) {
                String sql = "SELECT * FROM Role";
                try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Role role = new Role();
                        role.setRoleID(rs.getInt("roleID"));
                        role.setRole_name(rs.getString("role_name"));
                        role.setRoleAuthority(rs.getString("roleAuthority"));
                        roles.add(role);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving roles", ex);
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Error closing connection", ex);
                }
            }
        }
        return roles;
    }
}
