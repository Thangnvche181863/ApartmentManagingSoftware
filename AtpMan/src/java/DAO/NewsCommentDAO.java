/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.DBContext;
import model.NewsComment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author PC
 */
public class NewsCommentDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(NewsDAO.class.getName());

    public List<NewsComment> getAll() {
        List<NewsComment> list = new ArrayList<>();
        String sql = "SELECT * FROM NewsComment"; // Modify to your actual table name

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
                int commentID = rs.getInt("commentID");
                int newsID = rs.getInt("newsID");
                Integer customerID = rs.getObject("customerID") != null ? rs.getInt("customerID") : null; // Handle nullable
                Integer staffID = rs.getObject("staffID") != null ? rs.getInt("staffID") : null; // Handle nullable
                String commentText = rs.getString("commentText");
                java.sql.Timestamp commentDate = rs.getTimestamp("commentDate");

                NewsComment comment = new NewsComment(commentID, newsID, customerID, staffID, commentText, commentDate);
                list.add(comment);
            }

            // Close resources
            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} comment records.", list.size());

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching comment records.", e);
        }

        return list;
    }

    public List<NewsComment> getByNewsID(int newsID) {
        List<NewsComment> list = new ArrayList<>();
        String sql = "SELECT nc.*, c.username AS customerName, s.username AS staffName "
                + "FROM NewsComment nc "
                + "LEFT JOIN Customer c ON nc.customerID = c.customerID "
                + "LEFT JOIN Staff s ON nc.staffID = s.staffID "
                + "WHERE nc.newsID = ?"; // Use table aliases for clarity

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return list;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setInt(1, newsID); // Set the newsID parameter
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int commentID = rs.getInt("commentID");
                Integer customerID = rs.getObject("customerID") != null ? rs.getInt("customerID") : null; // Handle nullable
                Integer staffID = rs.getObject("staffID") != null ? rs.getInt("staffID") : null; // Handle nullable
                String commentText = rs.getString("commentText");
                java.sql.Timestamp commentDate = rs.getTimestamp("commentDate");
                String customerName = rs.getString("customerName"); // Retrieve customer name
                String staffName = rs.getString("staffName"); // Retrieve staff name

                // Create a new NewsComment object with the retrieved data
                NewsComment comment = new NewsComment(commentID, newsID, customerID, staffID, commentText, commentDate, customerName, staffName);
                list.add(comment);
            }

            // Close resources
            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} comment records for newsID: {1}.", new Object[]{list.size(), newsID});

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching comment records for newsID: " + newsID, e);
        }

        return list;
    }

    public boolean addNewsComment(NewsComment comment) throws ClassNotFoundException {
        String sql = "INSERT INTO NewsComment (newsID, customerID, staffID, commentDate, commentText) VALUES (?, ?, ?, ?, ?)";
        boolean isAdded = false;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return isAdded;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setInt(1, comment.getNewsID());

            // Set customerID
            if (comment.getCustomerID() != null) {
                pre.setInt(2, comment.getCustomerID());
            } else {
                pre.setNull(2, java.sql.Types.INTEGER); // Handle null for customerID
            }

            // Set staffID
            if (comment.getStaffID() != null) {
                pre.setInt(3, comment.getStaffID());
            } else {
                pre.setNull(3, java.sql.Types.INTEGER); // Handle null for staffID
            }

            pre.setTimestamp(4, new Timestamp(comment.getCommentDate().getTime())); // Assuming comment.getCommentDate() returns a java.util.Date object
            pre.setString(5, comment.getCommentText());

            // Execute the update
            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                LOGGER.log(Level.INFO, "Added comment for News ID: {0}", comment.getNewsID());
                isAdded = true;
            } else {
                LOGGER.log(Level.WARNING, "No comment was added.");
            }

            // Close resources
            pre.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding news comment.", e);
        }

        return isAdded;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        NewsCommentDAO newsCommentDAO = new NewsCommentDAO();

//        // Fetch all news comments
//        List<NewsComment> comments = newsCommentDAO.getAll();
//         if (comments.isEmpty()) {
//        System.out.println("No comments found.");
//    } else {
//        
//        for (NewsComment comment : comments) {
//            System.out.println("Comment ID: " + comment.getCommentID());
//            System.out.println("News ID: " + comment.getNewsID());
//            System.out.println("Customer ID: " + comment.getCustomerID());
//            System.out.println("Staff ID: " + comment.getStaffID());
//            System.out.println("Comment Text: " + comment.getCommentText());
//            System.out.println("Comment Date: " + comment.getCommentDate());
//            System.out.println("---------------------------------------");
//        }
//    }
//        //test get by newsID
//        int testNewsID = 45; // Change this to a valid newsID you want to test
//        System.out.println("\nFetching comments for newsID: " + testNewsID);
//        List<NewsComment> commentsByNewsID = newsCommentDAO.getByNewsID(testNewsID);
//        if (commentsByNewsID.isEmpty()) {
//            System.out.println("No comments found for newsID: " + testNewsID);
//        } else {
//            for (NewsComment comment : commentsByNewsID) {
//                System.out.println("Comment ID: " + comment.getCommentID());
//                System.out.println("News ID: " + comment.getNewsID());
//                System.out.println("Customer ID: " + comment.getCustomerID());
//                System.out.println("Staff ID: " + comment.getStaffID());
//                System.out.println("Comment Text: " + comment.getCommentText());
//                System.out.println("Comment Date: " + comment.getCommentDate());
//                System.out.println("---------------------------------------");
//            }
//        }

 NewsComment newComment = new NewsComment();
        newComment.setNewsID(41); // Set a valid newsID
        newComment.setCustomerID(null); // Set customerID to null
        newComment.setStaffID(2); // Set staffID to null
        newComment.setCommentText("This is a test comment.");
        newComment.setCommentDate(new Timestamp(Calendar.getInstance().getTimeInMillis())); // Current time

        // Test the addNewsComment method
        boolean result = newsCommentDAO.addNewsComment(newComment);

        // Check the result
        if (result) {
            System.out.println("Comment added successfully.");
        } else {
            System.out.println("Failed to add comment.");
        }
    }
    
}
