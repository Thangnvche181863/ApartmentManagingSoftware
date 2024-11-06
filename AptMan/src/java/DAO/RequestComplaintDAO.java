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

//    public List<RequestComplaint> getAllComplaints() {
//        Connection conn = null;
//        List<RequestComplaint> complaintList = new ArrayList<>();
//
//        try {
//            conn = DBContext.getConnection();
//            String sql = "SELECT rc.requestID, rc.customerID, rc.title, rc.description, rc.status, rc.dateRequested, rc.type, rc.serviceID, c.name AS customerName "
//                    + "FROM RequestComplaint rc "
//                    + "JOIN Customer c ON rc.customerID = c.customerID "
//                    + "ORDER BY requestID DESC";
////                    + "JOIN Service s ON rc.serviceID = s.serviceID";
//            
//        
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                int requestID = rs.getInt("requestID");
//                int customerID = rs.getInt("customerID");
//                String title = rs.getString("title");
//                String description = rs.getString("description");
//                int status = rs.getInt("status");
//                Date dateRequested = rs.getDate("dateRequested");
//                String type = rs.getString("type");
//                String customerName = rs.getString("customerName");
////                String serviceName = rs.getString("serviceName");
////                String serviceType = rs.getString("serviceType");
//                RequestComplaint complaint = new RequestComplaint(requestID, customerID, title, description, status, dateRequested, type, customerName);
//                complaintList.add(complaint);
//                System.out.println("Complaint:" + complaintList);
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            LOGGER.log(Level.SEVERE, null, e);
//        } finally {
//            DBContext.closeConnection(conn);
//        }
//        return complaintList;
//    }
    public List<RequestComplaint> getComplaints(String search, String searchField, String sort) {
        Connection conn = null;
        List<RequestComplaint> complaintList = new ArrayList<>();

        try {
            conn = DBContext.getConnection();
            String sql = "SELECT rc.requestID, rc.customerID, rc.title, rc.description, rc.status, rc.dateRequested, rc.type, c.name AS customerName "
                    + "FROM RequestComplaint rc "
                    + "JOIN Customer c ON rc.customerID = c.customerID ";

            // Thêm điều kiện tìm kiếm nếu có
            if (search != null && !search.isEmpty()) {
                sql += "WHERE ";
                if ("customerName".equals(searchField)) {
                    sql += "c.name LIKE ?";
                } else if ("type".equals(searchField)) {
                    sql += "rc.type LIKE ?";
                } else if ("status".equals(searchField)) {
                    sql += "rc.status LIKE ?";
                } else if ("title".equals(searchField)) {
                    sql += "rc.title LIKE ?";
                }
                sql += " ";  // Thêm khoảng trắng để nối với sắp xếp
            }

            // Thêm điều kiện sắp xếp
            if (sort != null && !sort.isEmpty()) {
                sql += " ORDER BY ";
                if ("date".equals(sort)) {
                    sql += "rc.dateRequested DESC";
                } else if ("customerName".equals(sort)) {
                    sql += "c.name ASC";
                } else if ("type".equals(sort)) {
                    sql += "rc.type ASC";
                } else if ("status".equals(sort)) {
                    sql += "rc.status ASC";
                }
            } else {
                sql += " ORDER BY rc.requestID DESC";  // Mặc định sắp xếp theo ID
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            int parameterIndex = 1;

            // Thiết lập tham số tìm kiếm
            if (search != null && !search.isEmpty()) {
                ps.setString(parameterIndex++, "%" + search + "%");
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

    public RequestComplaint getComplaintByID(int requestID) {
        Connection conn = null;
        RequestComplaint complaint = null;
        try {
            conn = DBContext.getConnection();
            String sql = "SELECT * FROM RequestComplaint WHERE requestID = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, requestID);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        complaint = new RequestComplaint();
                        complaint.setRequestID(rs.getInt("requestID"));
                        complaint.setCustomerID(rs.getInt("customerID"));
                        complaint.setTitle(rs.getString("title"));
                        complaint.setDescription(rs.getString("description"));
                        complaint.setType(rs.getString("type"));
                        complaint.setDateRequested(rs.getDate("dateRequested"));
                        complaint.setStatus(rs.getInt("status"));
                    }
                } catch (Exception e) {
                }

            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error getting customerID", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return complaint;
    }

    public List<RequestComplaint> getComplaintsByCustomer(int customerID) {
        Connection conn = null;
        List<RequestComplaint> complaints = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM RequestComplaint WHERE customerID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {  
                    ps.setInt(1, customerID);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        RequestComplaint complaint = new RequestComplaint();                       
                        complaint.setRequestID(rs.getInt("requestID"));
                        complaint.setCustomerID(rs.getInt("customerID"));
                        complaint.setTitle(rs.getString("title"));
                        complaint.setDescription(rs.getString("description"));
                        complaint.setDateRequested(rs.getDate("dateRequested"));
                        complaint.setStatus(rs.getInt("status"));                       
                        complaints.add(complaint);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error getting complaints for customerID: " + customerID, e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return complaints;
    }

}
