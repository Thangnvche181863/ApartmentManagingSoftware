/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Task;

/**
 *
 * @author WuanTun
 */
public class TaskDAO {

    private static final Logger LOGGER = Logger.getLogger(TaskDAO.class.getName());

    public int createTask(String taskName, String taskType, String description) {
        Connection conn = null;
        int generatedID = -1;
        try {
            conn = DBContext.getConnection();
            String sql = "INSERT INTO Task (taskName, description, taskType) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, taskName);
                ps.setString(2, description);
                ps.setString(3, taskType);
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedID = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error creating task", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return generatedID;
    }

    public List<Task> getTaskByStaffID(int staffID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        List<Task> taskList = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            String sql = "SELECT t.taskID, t.taskName, t.taskType, t.description " +
                     "FROM Assignment a " +
                     "JOIN Task t ON a.taskID = t.taskID " +
                     "WHERE a.staffID = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, staffID);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Task task = new Task();
                        task.setTaskID(rs.getInt("taskID"));
                        task.setTaskName(rs.getString("taskName"));
                        task.setTaskType(rs.getString("taskType"));
                        task.setDescription(rs.getString("description"));

                        taskList.add(task);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error checking password", e);
        }
        return taskList;
    }
}
