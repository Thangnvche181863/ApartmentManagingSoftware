/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.DBContext;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WuanTun
 */
public class AssignmentDAO {

    private static final Logger LOGGER = Logger.getLogger(AssignmentDAO.class.getName());
    
    public void createAssignment( int taskID){
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            String sql = "INSERT INTO Assignment ( taskID) VALUES ( ?)";
             try (PreparedStatement ps = conn.prepareStatement(sql)) {
//                ps.setInt(1, staffID);
                ps.setInt(1, taskID);
                ps.executeUpdate();
                
             }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error creating new assignment", e);
        } finally {
            DBContext.closeConnection(conn);
        }
    }

    
}
