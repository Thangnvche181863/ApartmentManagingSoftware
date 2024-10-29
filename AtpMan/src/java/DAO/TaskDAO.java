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

        String sql = "SELECT t.taskID, t.taskName, t.description, t.taskType\n"
                + "FROM Task t\n"
                + "LEFT JOIN Assigment a ON t.taskID = a.taskID\n"
                + "WHERE a.staffID IS NULL;";
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

    public int createTask(String taskName, String description, String taskType) {
        int n = 0;
        String sql = "INSERT INTO Task (taskName, description, taskType)\n"
                + "VALUES \n"
                + "    (?,?,?)";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, taskName);
            pre.setString(2, description);
            pre.setString(3, taskType);
            n = pre.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public List<String> getAllTaskType() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT taskType FROM Task; ";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {

                String taskType = rs.getString(1);

                list.add(taskType);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<Task> taskPaging(int page, int recordsPerPage) {
        List<Task> list = new ArrayList<>();
        Connection conn = null;
        try {
            int offset = (page - 1) * recordsPerPage;
            String sql = "SELECT t.taskID, t.taskName, t.description, t.taskType\n"
                + "FROM Task t\n"
                + "LEFT JOIN Assigment a ON t.taskID = a.taskID\n"
                + "WHERE a.staffID IS NULL order by taskID offset ? rows fetch next ? rows only";
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, offset);
            ps.setInt(2, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int taskID = rs.getInt(1);
                String taskName = rs.getString(2);
                String description = rs.getString(3);
                String taskType = rs.getString(4);

                Task task = new Task(taskID, taskName, description, taskType);
                list.add(task);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int count(int recordsPerPage) {
        int totalPages = 0;
        Connection conn = null;
        try {
            String sql = "select count(*) from task  ";
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int totalRecords = rs.getInt(1);
            totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
        } catch (Exception e) {
            System.out.println(e);
        }
        return totalPages;
    }

    public int getAmountOfTask() {
        TaskDAO dao = new TaskDAO();
        List<Task> list = dao.getAllTask();
        return list.size();
    }

    public List<Task> getTaskByType(String taskType, String search, String orderBy, int page, int recordsPerPage) {
        List<Task> listTask = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            int offset = (page - 1) * recordsPerPage;
            StringBuilder sql = new StringBuilder("SELECT * FROM Task WHERE 1 = 1 ");

            // Nếu roleID không phải là 0, thêm điều kiện lọc theo roleID
            if (taskType != null && !"0".equals(taskType)) {
                sql.append("AND taskType = ? ");
            }

            // Kiểm tra nếu có từ khóa tìm kiếm
            if (search != null && !search.isEmpty()) {
                sql.append("AND taskName COLLATE Latin1_General_CI_AI LIKE ? ");
            }

            // Thêm điều kiện sắp xếp
            if ("asc".equalsIgnoreCase(orderBy)) {
                sql.append("ORDER BY taskName ASC ");
            } else if ("desc".equalsIgnoreCase(orderBy)) {
                sql.append("ORDER BY taskName DESC ");
            } else {
                sql.append("ORDER BY taskID ");
            }

            // Thêm giới hạn phân trang
            sql.append("OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            // Thiết lập giá trị cho các tham số
            int paramIndex = 1;

            // Nếu có type, thiết lập tham số cho nó
            if (taskType != null && !"0".equals(taskType)) {
                ps.setString(paramIndex++, taskType);
                System.out.println("Total parameters: " + paramIndex);
            }

            // Nếu có từ khóa tìm kiếm, thiết lập tham số cho nó
            if (search != null && !search.isEmpty()) {
                ps.setString(paramIndex++, "%" + search + "%");
                System.out.println("SQL Query: " + sql.toString());

            }

            ps.setInt(paramIndex++, offset);
            ps.setInt(paramIndex, recordsPerPage);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTask.add(new Task(
                        rs.getInt("taskID"),
                        rs.getString("taskName"),
                        rs.getString("description"),
                        rs.getString("taskType")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listTask;
    }

    public int countActive(String taskType, String search, int recordsPerPage) {
        Connection conn = null;
        int totalPages = 0;
        try {
            conn = DBContext.getConnection();
            // Xây dựng câu lệnh SQL động tùy thuộc vào roleID và name
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Task WHERE 1=1");

            // Nếu roleID không bằng 0, thêm điều kiện lọc theo roleID
            if (taskType != null) {
                sql.append(" AND taskType = ?");
            }

            // Nếu search không rỗng, thêm điều kiện lọc theo username hoặc name
            if (search != null && !search.isEmpty()) {
                sql.append(" AND taskName COLLATE Latin1_General_CI_AI LIKE ? ");
            }

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (taskType != null) {
                ps.setString(paramIndex++, taskType);
            }
            if (search != null && !search.isEmpty()) {
                ps.setString(paramIndex++, "%" + search + "%");
                ps.setString(paramIndex, "%" + search + "%");
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int totalRecords = rs.getInt(1);
                totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return totalPages;
    }
    public static void main(String[] args) {
        TaskDAO dao = new TaskDAO();
        int n = dao.createTask("aaaa", "aaaa", "Bảo trì");
        System.out.println("Húp" +n);
    }
}
