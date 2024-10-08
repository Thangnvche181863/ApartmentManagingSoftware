/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.DBContext;
import model.Assignment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author PC
 */
public class AssignmentDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(AssignmentDAO.class.getName());

    // Retrieve all assignments
    public List<Assignment> getAll() {
        List<Assignment> list = new ArrayList<>();
        String sql = "SELECT * FROM Assignment";

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return list;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int staffID = rs.getInt("staffID");
                int taskID = rs.getInt("taskID");
                java.sql.Timestamp sqlStartTime = rs.getTimestamp("startTime");
                java.sql.Timestamp sqlEndTime = rs.getTimestamp("endTime");

                Date startTime = new Date(sqlStartTime.getTime());
                Date endTime = new Date(sqlEndTime.getTime());

                Assignment assignment = new Assignment(staffID, taskID, startTime, endTime);
                list.add(assignment);
            }

            // Close resources
            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} assignments.", list.size());

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching assignments.", e);
        }

        return list;
    }

    public boolean add(Assignment assignment) {
        String sql = "INSERT INTO Assignment (staffID, taskID, startTime, endTime) VALUES (?, ?, ?, ?)";
        boolean isAdded = false;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return isAdded;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setInt(1, assignment.getStaffID());
            pre.setInt(2, assignment.getTaskID());
            pre.setTimestamp(3, new java.sql.Timestamp(assignment.getStartTime().getTime()));
            pre.setTimestamp(4, new java.sql.Timestamp(assignment.getEndTime().getTime()));

            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                LOGGER.log(Level.INFO, "Successfully added assignment.");
                isAdded = true;
            } else {
                LOGGER.log(Level.WARNING, "No assignment was added.");
            }

            // Close resources
            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error adding assignment.", e);
        }

        return isAdded;
    }
    //delete by staffID and taskID

    public boolean delete(int staffID, int taskID) {
        String sql = "DELETE FROM Assignment WHERE staffID = ? AND taskID = ?";
        boolean isDeleted = false;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return isDeleted;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setInt(1, staffID);
            pre.setInt(2, taskID);

            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                LOGGER.log(Level.INFO, "Successfully deleted assignment with StaffID: {0} and TaskID: {1}", new Object[]{staffID, taskID});
                isDeleted = true;
            } else {
                LOGGER.log(Level.WARNING, "No assignment found to delete with StaffID: {0} and TaskID: {1}", new Object[]{staffID, taskID});
            }

            // Close resources
            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error deleting assignment.", e);
        }

        return isDeleted;
    }

    //update 
    public boolean update(Assignment assignment) {
        String sql = "UPDATE Assignment SET startTime = ?, endTime = ? WHERE staffID = ? AND taskID = ?";
        boolean isUpdated = false;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return isUpdated;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setTimestamp(1, new java.sql.Timestamp(assignment.getStartTime().getTime()));
            pre.setTimestamp(2, new java.sql.Timestamp(assignment.getEndTime().getTime()));
            pre.setInt(3, assignment.getStaffID());
            pre.setInt(4, assignment.getTaskID());

            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                LOGGER.log(Level.INFO, "Successfully updated assignment.");
                isUpdated = true;
            } else {
                LOGGER.log(Level.WARNING, "No assignment found with StaffID: {0} and TaskID: {1}", new Object[]{assignment.getStaffID(), assignment.getTaskID()});
            }

            // Close resources
            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error updating assignment.", e);
        }

        return isUpdated;
    }

    public static void main(String[] args) {
        AssignmentDAO dao = new AssignmentDAO();
        Assignment ass = new Assignment();

        //test get all
//         List<Assignment> assignments = dao.getAll();
//        System.out.println("\nAll Assignments:");
//        for (Assignment assignment : assignments) {
//            System.out.println("StaffID: " + assignment.getStaffID() +
//                               ", TaskID: " + assignment.getTaskID() +
//                               ", StartTime: " + assignment.getStartTime() +
//                               ", EndTime: " + assignment.getEndTime());
//        }
        //test add
        Assignment newAssignment = new Assignment();
//        newAssignment.setStaffID(1); // Ensure this staffID exists in your Staff table
//        newAssignment.setTaskID(1); // Ensure this taskID exists in your Task table
//        newAssignment.setStartTime(new Date()); // Current date and time
//        // Set endTime to one hour later
//        Date endTime = new Date(newAssignment.getStartTime().getTime() + 3600 * 1000);
//        newAssignment.setEndTime(endTime);
//
//        boolean isAdded = dao.add(newAssignment);
//        if (isAdded) {
//            System.out.println("Added Assignment: StaffID=" + newAssignment.getStaffID() +
//                               ", TaskID=" + newAssignment.getTaskID() +
//                               ", StartTime=" + newAssignment.getStartTime() +
//                               ", EndTime=" + newAssignment.getEndTime());
//        } else {
//            System.out.println("Failed to add Assignment.");
//        }

        //test delete by staffID and taskID
//        newAssignment.setStaffID(1); // Ensure this staffID exists in your Staff table
//        newAssignment.setTaskID(1); // Ensure this taskID exists in your Task table
//        boolean isDeleted = dao.delete(newAssignment.getStaffID(), newAssignment.getTaskID());
//        if (isDeleted) {
//            System.out.println("\nDeleted Assignment: StaffID=" + newAssignment.getStaffID()
//                    + ", TaskID=" + newAssignment.getTaskID());
//        } else {
//            System.out.println("\nFailed to delete Assignment.");
//        }



        //test update EndTime
//        newAssignment.setStaffID(1); // Ensure this staffID exists in your Staff table
//        newAssignment.setTaskID(1); // Ensure this taskID exists in your Task table
//        newAssignment.setStartTime(new Date());
//        newAssignment.setEndTime(new Date(newAssignment.getStartTime().getTime() + 7200 * 1000)); // Extend by another hour
//        boolean isUpdated = dao.update(newAssignment);
//        if (isUpdated) {
//            System.out.println("\nUpdated Assignment: StaffID=" + newAssignment.getStaffID()
//                    + ", TaskID=" + newAssignment.getTaskID()
//                    + ", New EndTime=" + newAssignment.getEndTime());
//        } else {
//            System.out.println("\nFailed to update Assignment.");
//        }
    }
}
