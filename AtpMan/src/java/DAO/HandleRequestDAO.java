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
public class HandleRequestDAO {

    private static final Logger LOGGER = Logger.getLogger(HandleRequestDAO.class.getName());

    public int createHandleRequest1( int staffID) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            String sql = "INSERT INTO HandleRequest (staffID) VALUES (?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, staffID);
                ps.executeUpdate();

            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error creating new handlerequest", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return -1;
    }

    public void createHandleRequest2(int requestID, int taskID) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            String sql = "INSERT INTO HandleRequest (requestID, taskID) VALUES ( ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, requestID);
                ps.setInt(2, taskID);
                ps.executeUpdate();

            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error creating new handlerequest", e);
        } finally {
            DBContext.closeConnection(conn);
        }
    }
}
