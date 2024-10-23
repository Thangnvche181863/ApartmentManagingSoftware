/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author ADMIN
 */
public class RequestComplaintDAO {

    Connection connection = null;

    /**
     *
     * @return
     */
    public List<RequestComplaint> getAllRequest() {
        List<RequestComplaint> list = new ArrayList<>();
        String sql = "select * from RequestComplaint";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                RequestComplaint requestComplaint = new RequestComplaint();
                requestComplaint.setRequestID(rs.getInt(1));
                requestComplaint.setCustomerID(rs.getInt(2));
                requestComplaint.setTitle(rs.getString(3));
                requestComplaint.setDescription(rs.getString(4));
                requestComplaint.setStatus(rs.getInt(5));
                requestComplaint.setDateRequested(rs.getDate(6));
                requestComplaint.setType(rs.getString(7));
                list.add(requestComplaint);
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
        return list;
    }

    public static void main(String[] args) {
        RequestComplaintDAO dao = new RequestComplaintDAO();
        List<RequestComplaint> list = dao.getAllRequest();
        System.out.println(list.size());
    }
    //QUAN
    private static final Logger LOGGER = Logger.getLogger(RequestComplaintDAO.class.getName());

    public void submitComplaint(int customerID, String title, String description, String type) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO RequestComplaint (customerID, title, description, status, dateRequested, type) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, customerID);
                    ps.setString(2, title);
                    ps.setString(3, description);
                    ps.setInt(4, 0);
                    ps.setDate(5, new Date(System.currentTimeMillis()));
                    ps.setString(6, type);
                    ps.executeUpdate();
                    System.out.println("");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error insert complaint", e);
        } finally {
            DBContext.closeConnection(conn);
        }

    }

    public List<RequestComplaint> getAllComplaints(String search, String sort) {
        Connection conn = null;
        List<RequestComplaint> complaintList = new ArrayList<>();

        // Danh sách các cột có thể sắp xếp
        List<String> validSortColumns = Arrays.asList("requestID", "customerName", "dateRequested", "status", "type");

        if (!validSortColumns.contains(sort)) {
            sort = "requestID"; // Giá trị mặc định nếu sort không hợp lệ
        }

        try {
            conn = DBContext.getConnection();
            StringBuilder sql = new StringBuilder("SELECT rc.requestID, rc.customerID, rc.title, rc.description, rc.status, rc.dateRequested, rc.type, c.name AS customerName FROM RequestComplaint rc JOIN Customer c ON rc.customerID = c.customerID");

            // Thêm điều kiện tìm kiếm
            if (search != null && !search.isEmpty()) {
                sql.append(" WHERE c.name LIKE ? OR rc.title LIKE ?");
            }

            // Thêm điều kiện sắp xếp
            sql.append(" ORDER BY ").append(sort);

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            // Set các tham số tìm kiếm
            if (search != null && !search.isEmpty()) {
                ps.setString(1, "%" + search + "%");
                ps.setString(2, "%" + search + "%");
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int requestID = rs.getInt("requestID");
                int customerID = rs.getInt("customerID");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                Date dateRequested = rs.getDate("dateRequested");
                String type = rs.getString("type");
                String customerName = rs.getString("customerName");

                RequestComplaint complaint = new RequestComplaint(requestID, customerID, title, description, status, dateRequested, type, customerName);
                complaintList.add(complaint);
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, null, e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return complaintList;
    }

    public String getCustomerNameByID(int customerID) {
        Connection conn = null;
        String customerName = null;
        try {
            conn = DBContext.getConnection();
            String sql = "SELECT name FROM Customer WHERE customerID = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, customerID);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        customerName = rs.getString("name");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error finding name by customerID", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return customerName;
    }

    public void updateStatus(int requestID, int status) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "UPDATE RequestComplaint SET status = ? WHERE requestID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, status);
                    ps.setInt(2, requestID);
                    ps.executeUpdate();
                }

            }

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error insert complaint", e);
        } finally {
            DBContext.closeConnection(conn);
        }
    }
}
