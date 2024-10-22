/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Apartment;
import model.Building;
import model.Task;
import utils.DBContext;

/**
 *
 * @author Admin
 */
public class TaskDAO {

    Connection conn = null;

    public List<Task> getAllTask() {
        List<Task> list = new ArrayList<>();

        String sql = "select * from Task";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int taskID = rs.getInt(1);
                String taskName = rs.getString(2);
                String description = rs.getString(3);
                String taskType = rs.getString(4);
                Task task = new Task(taskID, taskName, description, taskType);
                list.add(task);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return list;
    }
    public List<String> getAllTaskType(){
        List list = new ArrayList<>();
        String sql = "";
        
        
        
        return list;
    }
    public int getAmountOfTask(){
        TaskDAO dao = new TaskDAO();
        List<Task> list = dao.getAllTask();
        return list.size();
    }

}
