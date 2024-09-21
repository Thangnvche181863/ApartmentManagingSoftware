/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.DBContext;
import model.News;
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
public class NewsDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(NewsDAO.class.getName());
//get all

    public List<News> getAll() {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News";

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
                int newsID = rs.getInt("newsID");
                int staffID = rs.getInt("staffID");
                int taskID = rs.getInt("taskID");
                int newsCategoryID = rs.getInt("newsCategoryID");
                String newsTitle = rs.getString("newsTitle");
                String newsContent = rs.getString("newsContent");
                java.sql.Timestamp sqlPostDate = rs.getTimestamp("postDate");
                Date postDate = new Date(sqlPostDate.getTime());
                String newsImg = rs.getString("newsImg");

                News news = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg);
                list.add(news);
            }

            // Close resources
            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} news records.", list.size());

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching news records.", e);
        }

        return list;
    }

    public List<News> getNewsByPage(int page, int recordsPerPage) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT n.*, nc.name as newsCategoryName, s.name as staffName "
                + "FROM News n "
                + "JOIN NewsCategory nc ON n.newsCategoryID = nc.newsCategoryID "
                + "JOIN Staff s ON n.staffID = s.staffID "
                + "ORDER BY n.newsID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return list;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setInt(1, (page - 1) * recordsPerPage); // Calculate offset
            pre.setInt(2, recordsPerPage);              // Set the limit
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int newsID = rs.getInt("newsID");
                int staffID = rs.getInt("staffID");
                int taskID = rs.getInt("taskID");
                int newsCategoryID = rs.getInt("newsCategoryID");
                String newsTitle = rs.getString("newsTitle");
                String newsContent = rs.getString("newsContent");
                java.sql.Timestamp sqlPostDate = rs.getTimestamp("postDate");
                Date postDate = new Date(sqlPostDate.getTime());
                String newsImg = rs.getString("newsImg");
                String newsCategoryName = rs.getString("newsCategoryName");
                String staffName = rs.getString("staffName");

                News news = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg, newsCategoryName, staffName);
                list.add(news);
            }

            // Close resources
            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} news records for page {1}.", new Object[]{list.size(), page});

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching paginated news records.", e);
        }

        return list;
    }

    public int getNumberOfRows() {
        String sql = "SELECT COUNT(*) FROM News";
        int count = 0;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return count;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1); // Get the count of rows
            }

            // Close resources
            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Total number of news records: {0}", count);

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error getting the number of news records.", e);
        }

        return count;
    }

    public News getNewsById(int newsID) {
        News news = null;
        String sql = "SELECT n.*, nc.name as newsCategoryName, s.name as staffName "
            + "FROM News n "
            + "JOIN NewsCategory nc ON n.newsCategoryID = nc.newsCategoryID "
            + "JOIN Staff s ON n.staffID = s.staffID "
            + "WHERE n.newsID = ?";

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return null;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setInt(1, newsID); // Set the newsID parameter
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                int staffID = rs.getInt("staffID");
                int taskID = rs.getInt("taskID");
                int newsCategoryID = rs.getInt("newsCategoryID");
                String newsTitle = rs.getString("newsTitle");
                String newsContent = rs.getString("newsContent");
                java.sql.Timestamp sqlPostDate = rs.getTimestamp("postDate");
                Date postDate = new Date(sqlPostDate.getTime());
                String newsImg = rs.getString("newsImg");
                String newsCategoryName = rs.getString("newsCategoryName");
                String staffName = rs.getString("staffName");

                news = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg, newsCategoryName, staffName);
            }

            rs.close();
            pre.close();
            LOGGER.log(Level.INFO, "Successfully retrieved news with ID {0}.", newsID);

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching news by ID.", e);
        }

        return news;
    }

    public static void main(String[] args) {
        NewsDAO dao = new NewsDAO();
        News newNews = new News();
//        newNews.setStaffID(1); // Assuming staffID 1 exists
//        newNews.setTaskID(1); // Assuming taskID 1 exists
//        newNews.setNewsCategoryID(1); // Assuming newsCategoryID 1 exists
//        newNews.setNewsTitle("New Feature Released");
//        newNews.setNewsContent("We have released a new feature that allows users to...");
//        newNews.setPostDate(new java.util.Date());
//        newNews.setNewsImg("img/cutepic.png");
//
//        boolean isAdded = dao.addNews(newNews);
//    if (isAdded) {
//        System.out.println("Added News with ID: " + newNews.getNewsID());
//    } else {
//        System.out.println("Failed to add News.");
//    }
        //test get all
        List<News> newsList = dao.getAll();
//        if (!newsList.isEmpty()) {
//            for (News news : newsList) {
//                System.out.println("ID: " + news.getNewsID()
//                        + ", Title: " + news.getNewsTitle()
//                        + ", Content: " + news.getNewsContent()
//                        + ", Post Date: " + news.getPostDate()
//                        + ", Image URL: " + news.getNewsImg());
//            }
//        } else {
//            System.out.println("No news records found.");
//        }

        // Test getNumberOfRows
        int totalRows = dao.getNumberOfRows();
        System.out.println("Total number of news records: " + totalRows);

        // Determine number of pages
        int recordsPerPage = 5;
        int totalPages = (int) Math.ceil((double) totalRows / recordsPerPage);
        System.out.println("Total number of pages: " + totalPages);

        // Test getNewsByPage for page 1
        int pageToTest = 1; // You can change this to test different pages
        System.out.println("Fetching news for page " + pageToTest);
        List<News> paginatedNews = dao.getNewsByPage(pageToTest, recordsPerPage);

        if (!paginatedNews.isEmpty()) {
            for (News news : paginatedNews) {
                System.out.println("ID: " + news.getNewsID()
                        + ", Title: " + news.getNewsTitle()
                        + ", Content: " + news.getNewsContent()
                        + ", Post Date: " + news.getPostDate()
                        + ", Image URL: " + news.getNewsImg());
            }
        } else {
            System.out.println("No news records found for page " + pageToTest);
        }
    }
}
