/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import utils.DBContext;
import model.Assignment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.DBContext;

/**
 *
 * @author Admin
 */
public class AssignmentDAO {

    public int creatAssignment(int staffID, int taskID, Date startTime, Date endTime, String status) {
        Connection conn = null;

        int n = 0;
        String sql = "INSERT INTO Assignment (staffID, taskID, startTime, endTime, status) VALUES (?, ?, ?, ?, ?)";

        try {
            // Thiết lập kết nối
            conn = DBContext.getConnection();
            PreparedStatement pre = null;
            // Chuẩn bị câu lệnh SQL
            pre = conn.prepareStatement(sql);

            // Gán các giá trị cho các tham số
            pre.setInt(1, staffID);
            pre.setInt(2, taskID);
            pre.setDate(3, startTime);
            pre.setDate(4, endTime);
            pre.setString(5, status);
            // Thực hiện câu lệnh và lấy số hàng ảnh hưởng
            n = pre.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return n;
    }

    private static final Logger LOGGER = Logger.getLogger(AssignmentDAO.class.getName());

    public void createAssignment(int taskID) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            String sql = "INSERT INTO Assignment ( taskID) VALUES ( ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                // ps.setInt(1, staffID);
                ps.setInt(1, taskID);
                ps.executeUpdate();

            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error creating new assignment", e);
        } finally {
            DBContext.closeConnection(conn);
        }
    }

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
                String status = rs.getString("status");
                Date startTime = new Date(sqlStartTime.getTime());
                Date endTime = new Date(sqlEndTime.getTime());

                Assignment assignment = new Assignment(staffID, taskID, startTime, endTime, status);
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
    // delete by staffID and taskID

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
                LOGGER.log(Level.INFO, "Successfully deleted assignment with StaffID: {0} and TaskID: {1}",
                        new Object[] { staffID, taskID });
                isDeleted = true;
            } else {
                LOGGER.log(Level.WARNING, "No assignment found to delete with StaffID: {0} and TaskID: {1}",
                        new Object[] { staffID, taskID });
            }

            // Close resources
            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error deleting assignment.", e);
        }

        return isDeleted;
    }

    // update
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
                LOGGER.log(Level.WARNING, "No assignment found with StaffID: {0} and TaskID: {1}",
                        new Object[] { assignment.getStaffID(), assignment.getTaskID() });
            }

            // Close resources
            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error updating assignment.", e);
        }

        return isUpdated;
    }

    public List<Assignment> AssignmentPaging(int page, int recordsPerPage) {
        List<Assignment> list = new ArrayList<>();
        Connection conn = null;
        try {
            int offset = (page - 1) * recordsPerPage;
            String sql = "	SELECT \n"
                    + "    S.name,              -- Tên của nhân viên\n"
                    + "    T.taskName,          -- Tên của task\n"
                    + "    A.startTime,\n"
                    + "    A.endTime,\n"
                    + "    A.status\n"
                    + "FROM \n"
                    + "    Assignment A\n"
                    + "JOIN \n"
                    + "    Staff S ON A.staffID = S.staffID\n"
                    + "JOIN \n"
                    + "    Task T ON A.taskID = T.taskID\n"
                    + "ORDER BY \n"
                    + "    name\n"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, offset);
            ps.setInt(2, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String staffName = rs.getString(1);
                String taskName = rs.getString(2);
                java.util.Date startTime = rs.getDate(3);
                java.util.Date endTime = rs.getDate(4);
                String status = rs.getString(5);

                Assignment assignment = new Assignment(staffName, taskName, startTime, endTime, status);
                list.add(assignment);
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
            String sql = "select count(*) from assignment  ";
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

    public List<Assignment> getAllAssignment() {
        List<Assignment> list = new ArrayList<>();
        Connection conn = null;
        try {

            String sql = "SELECT \n"
                    + "    S.name,            -- Tên của nhân viên\n"
                    + "    T.taskName,             -- Tên của task\n"
                    + "    A.startTime,\n"
                    + "    A.endTime,\n"
                    + "	A.status\n"
                    + "FROM \n"
                    + "    Assignment A\n"
                    + "JOIN \n"
                    + "    Staff S ON A.staffID = S.staffID\n"
                    + "JOIN \n"
                    + "    Task T ON A.taskID = T.taskID;";
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String staffName = rs.getString(1);
                String taskName = rs.getString(2);
                java.util.Date startTime = rs.getDate(3);
                java.util.Date endTime = rs.getDate(4);
                String status = rs.getString(5);

                Assignment assignment = new Assignment(staffName, taskName, startTime, endTime, status);
                list.add(assignment);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getAmountOfAssignment() {
        AssignmentDAO dao = new AssignmentDAO();
        List<Assignment> list = dao.getAll();
        return list.size();
    }

    public List<Assignment> getAssginmentByStatus(String st) {
        List<Assignment> list = new ArrayList<>();
        Connection conn = null;
        try {

            String sql = "SELECT \n"
                    + "    S.name,            -- Tên của nhân viên\n"
                    + "    T.taskName,             -- Tên của task\n"
                    + "    A.startTime,\n"
                    + "    A.endTime,\n"
                    + "	A.status\n"
                    + "FROM \n"
                    + "    Assignment A\n"
                    + "JOIN \n"
                    + "    Staff S ON A.staffID = S.staffID\n"
                    + "JOIN \n"
                    + "    Task T ON A.taskID = T.taskID where status = ?;";
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, st);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String staffName = rs.getString(1);
                String taskName = rs.getString(2);
                java.util.Date startTime = rs.getDate(3);
                java.util.Date endTime = rs.getDate(4);
                String status = rs.getString(5);

                Assignment assignment = new Assignment(staffName, taskName, startTime, endTime, status);
                list.add(assignment);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Assignment> getAssignmentByType(String status, String search, String orderBy, int page,
            int recordsPerPage) {
        List<Assignment> listAssignment = new ArrayList<>();
        System.out.println(status);
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            int offset = (page - 1) * recordsPerPage;
            StringBuilder sql = new StringBuilder("SELECT \n"
                    + "    S.name,            \n"
                    + "    T.taskName,             \n"
                    + "    A.startTime,\n"
                    + "    A.endTime,\n"
                    + "    A.status\n"
                    + "FROM \n"
                    + "    Assignment A\n"
                    + "JOIN \n"
                    + "    Staff S ON A.staffID = S.staffID\n"
                    + "JOIN \n"
                    + "    Task T ON A.taskID = T.taskID where 1=1 ");

            // Nếu roleID không phải là 0, thêm điều kiện lọc theo roleID
            if (status != null && !"0".equals(status)) {
                sql.append("AND A.status = ? ");
            }

            // Kiểm tra nếu có từ khóa tìm kiếm
            if (search != null && !search.isEmpty()) {
                sql.append("AND T.taskName COLLATE Latin1_General_CI_AI LIKE ? ");
            }

            // Thêm điều kiện sắp xếp
            if ("asc".equalsIgnoreCase(orderBy)) {
                sql.append("ORDER BY T.taskName ASC ");
            } else if ("desc".equalsIgnoreCase(orderBy)) {
                sql.append("ORDER BY T.taskName DESC ");
            } else {
                sql.append("ORDER BY S.name ");
            }

            // Thêm giới hạn phân trang
            sql.append("OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            // Thiết lập giá trị cho các tham số
            int paramIndex = 1;

            // Nếu có status, thiết lập tham số cho nó
            if (status != null && !"0".equals(status)) {
                ps.setString(paramIndex++, status);
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
                listAssignment.add(new Assignment(
                        rs.getString("name"),
                        rs.getString("taskName"),
                        rs.getDate("startTime"),
                        rs.getDate("endTime"),
                        rs.getString("status")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listAssignment;
    }

    public int countActive(String status, String search, int recordsPerPage) {
        Connection conn = null;
        int totalPages = 0;
        try {
            conn = DBContext.getConnection();
            // Xây dựng câu lệnh SQL động tùy thuộc vào roleID và name
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Assignment WHERE 1=1");

            // Nếu roleID không bằng 0, thêm điều kiện lọc theo roleID
            if (status != null) {
                sql.append(" AND status = ?");
            }

            // Nếu search không rỗng, thêm điều kiện lọc theo username hoặc name
            if (search != null && !search.isEmpty()) {
                sql.append(" AND taskName COLLATE Latin1_General_CI_AI LIKE ? ");
            }

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (status != null) {
                ps.setString(paramIndex++, status);
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

    public int getNumberCompleteAssignment() {
        Connection conn = null;
        int n = 0;
        String sql = "SELECT COUNT(*) FROM Assignment WHERE status = 'Da hoan thanh'";

        try {
            // Thiết lập kết nối
            conn = DBContext.getConnection();
            // Chuẩn bị câu lệnh SQL
            PreparedStatement pre = conn.prepareStatement(sql);

            // Thực hiện câu lệnh và lấy kết quả
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1); // Lấy giá trị đầu tiên trong kết quả (COUNT(*))
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return n;
    }

    public static void main(String[] args) {
        AssignmentDAO dao = new AssignmentDAO();
        System.out.println("Húp" + dao.getNumberCompleteAssignment());

        // test get all
        // List<Assignment> assignments = dao.getAll();
        // System.out.println("\nAll Assignments:");
        // for (Assignment assignment : assignments) {
        // System.out.println("StaffID: " + assignment.getStaffID() +
        // ", TaskID: " + assignment.getTaskID() +
        // ", StartTime: " + assignment.getStartTime() +
        // ", EndTime: " + assignment.getEndTime());
        // }
        // test add
        // newAssignment.setStaffID(1); // Ensure this staffID exists in your Staff
        // table
        // newAssignment.setTaskID(1); // Ensure this taskID exists in your Task table
        // newAssignment.setStartTime(new Date()); // Current date and time
        // // Set endTime to one hour later
        // Date endTime = new Date(newAssignment.getStartTime().getTime() + 3600 *
        // 1000);
        // newAssignment.setEndTime(endTime);
        //
        // boolean isAdded = dao.add(newAssignment);
        // if (isAdded) {
        // System.out.println("Added Assignment: StaffID=" + newAssignment.getStaffID()
        // +
        // ", TaskID=" + newAssignment.getTaskID() +
        // ", StartTime=" + newAssignment.getStartTime() +
        // ", EndTime=" + newAssignment.getEndTime());
        // } else {
        // System.out.println("Failed to add Assignment.");
        // }
        // test delete by staffID and taskID
        // newAssignment.setStaffID(1); // Ensure this staffID exists in your Staff
        // table
        // newAssignment.setTaskID(1); // Ensure this taskID exists in your Task table
        // boolean isDeleted = dao.delete(newAssignment.getStaffID(),
        // newAssignment.getTaskID());
        // if (isDeleted) {
        // System.out.println("\nDeleted Assignment: StaffID=" +
        // newAssignment.getStaffID()
        // + ", TaskID=" + newAssignment.getTaskID());
        // } else {
        // System.out.println("\nFailed to delete Assignment.");
        // }
        // test update EndTime
        // newAssignment.setStaffID(1); // Ensure this staffID exists in your Staff
        // table
        // newAssignment.setTaskID(1); // Ensure this taskID exists in your Task table
        // newAssignment.setStartTime(new Date());
        // newAssignment.setEndTime(new Date(newAssignment.getStartTime().getTime() +
        // 7200 * 1000)); // Extend by another hour
        // boolean isUpdated = dao.update(newAssignment);
        // if (isUpdated) {
        // System.out.println("\nUpdated Assignment: StaffID=" +
        // newAssignment.getStaffID()
        // + ", TaskID=" + newAssignment.getTaskID()
        // + ", New EndTime=" + newAssignment.getEndTime());
        // } else {
        // System.out.println("\nFailed to update Assignment.");
        // }
    }
}
