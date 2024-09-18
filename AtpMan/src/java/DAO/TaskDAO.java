/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.DBContext;
import model.Task;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class TaskDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(TaskDAO.class.getName());

    public List<Task> getAll() {
        List<Task> list = new ArrayList<>();
        String sql = "SELECT * FROM Task";

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
                int taskID = rs.getInt("taskID");
                String taskName = rs.getString("taskName");
                String description = rs.getString("description");
                String taskType = rs.getString("taskType");

                Task task = new Task(taskID, taskName, description, taskType);
                list.add(task);
            }

            // Close resources
            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} task records.", list.size());

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching task records.", e);
        }

        return list;
    }

    public boolean addTask(Task task) {
        String sql = "INSERT INTO Task (taskName, description, taskType) VALUES (?, ?, ?)";
        boolean isAdded = false;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return isAdded;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pre.setString(1, task.getTaskName());
            pre.setString(2, task.getDescription());
            pre.setString(3, task.getTaskType());

            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        task.setTaskID(generatedKeys.getInt(1));
                        LOGGER.log(Level.INFO, "Added Task with ID: {0}", task.getTaskID());
                        isAdded = true;
                    } else {
                        throw new SQLException("Adding task failed, no ID obtained.");
                    }
                }
            } else {
                LOGGER.log(Level.WARNING, "No task was added.");
            }

            // Close resources
            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error adding task.", e);
        }

        return isAdded;
    }

    public boolean updateTask(Task task) {
        String sql = "UPDATE Task SET taskName = ?, description = ?, taskType = ? WHERE taskID = ?";
        boolean isUpdated = false;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return isUpdated;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setString(1, task.getTaskName());
            pre.setString(2, task.getDescription());
            pre.setString(3, task.getTaskType());
            pre.setInt(4, task.getTaskID());

            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                LOGGER.log(Level.INFO, "Updated Task with ID: {0}", task.getTaskID());
                isUpdated = true;
            } else {
                LOGGER.log(Level.WARNING, "No Task found with ID: {0}", task.getTaskID());
            }

            // Close resources
            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error updating task.", e);
        }

        return isUpdated;
    }
    
    public boolean deleteTask(int taskID) {
        String sql = "DELETE FROM Task WHERE taskID = ?";
        boolean isDeleted = false;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return isDeleted;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setInt(1, taskID);

            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                LOGGER.log(Level.INFO, "Deleted Task with ID: {0}", taskID);
                isDeleted = true;
            } else {
                LOGGER.log(Level.WARNING, "No Task found to delete with ID: {0}", taskID);
            }

            // Close resources
            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error deleting task.", e);
        }

        return isDeleted;
    }


    public static void main(String[] args) {
        TaskDAO dao = new TaskDAO();
        Task newTask = new Task();

        //test get all
//        List<Task> tasks = dao.getAll();
//        System.out.println("\nAll Tasks:");
//        for (Task task : tasks) {
//            System.out.println("TaskID: " + task.getTaskID()
//                    + ", TaskName: " + task.getTaskName()
//                    + ", Description: " + task.getDescription()
//                    + ", TaskType: " + task.getTaskType());
//        }
        //test add
//        newTask.setTaskName("Test");
//        newTask.setDescription("This is a test desc.");
//        newTask.setTaskType("Test");
//        boolean isAdded = dao.addTask(newTask);
//        if (isAdded) {
//            System.out.println("Added Task: TaskID=" + newTask.getTaskID() +
//                               ", TaskName=" + newTask.getTaskName() +
//                               ", Description=" + newTask.getDescription() +
//                               ", TaskType=" + newTask.getTaskType());
//        } else {
//            System.out.println("Failed to add Task.");
//        }
        //test update by id
//        newTask.setTaskName("Updated Test");
//        newTask.setDescription("This is an updated test desc.");
//        newTask.setTaskType("Test");
//        newTask.setTaskID(11);
//        boolean isUpdated = dao.updateTask(newTask);
//        if (isUpdated) {
//            System.out.println("\nUpdated Task: TaskID=" + newTask.getTaskID() +
//                               ", TaskName=" + newTask.getTaskName() +
//                               ", Description=" + newTask.getDescription() +
//                               ", TaskType=" + newTask.getTaskType());
//        } else {
//            System.out.println("\nFailed to update Task.");
//        }
          //delete 
//          newTask.setTaskID(12);
//          boolean isDeleted = dao.deleteTask(newTask.getTaskID());
//           if (isDeleted) {
//            System.out.println("\nDeleted Task: TaskID=" + newTask.getTaskID());
//        } else {
//            System.out.println("\nFailed to delete Task.");
//        }
//         
    }
}
