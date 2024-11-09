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

    public void createHandleRequest(int requestID, int taskID) {
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

//    public void createHandleRequest(int requestID, int staffID, int taskID) {
//        Connection conn = null;
//        try {
//            conn = DBContext.getConnection();
//            String sql = "INSERT INTO HandleRequest (requestID, staffID, taskID) VALUES (?, ?, ?)";
//            try (PreparedStatement ps = conn.prepareStatement(sql)) {
//                ps.setInt(1, requestID);
//                ps.setInt(2, staffID);
//                ps.setInt(3, taskID);
//                ps.executeUpdate();
//
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            LOGGER.log(Level.SEVERE, "Error creating new handlerequest", e);
//        } finally {
//            DBContext.closeConnection(conn);
//        }
//    }
}
