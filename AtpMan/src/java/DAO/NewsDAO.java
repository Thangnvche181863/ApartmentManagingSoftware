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
//news by page

    public List<News> getNewsByPage(int page, int recordsPerPage) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT n.*, nc.name as newsCategoryName, s.name as staffName "
                + "FROM News n "
                + "JOIN NewsCategory nc ON n.newsCategoryID = nc.newsCategoryID "
                + "JOIN Staff s ON n.staffID = s.staffID "
                + "WHERE n.newsCategoryID = 1 "
                + "ORDER BY n.postDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {

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
                String newsDescription = rs.getString("newsDescription");
                String newsCategoryName = rs.getString("newsCategoryName");
                String staffName = rs.getString("staffName");

                News news = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg, newsCategoryName, staffName, newsDescription);
                list.add(news);
            }

            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} news records for page {1}.", new Object[]{list.size(), page});

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching paginated news records.", e);
        }

        return list;
    }

    public List<News> getNewsByPageManage(int page, int recordsPerPage) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT n.*, nc.name as newsCategoryName, s.name as staffName "
                + "FROM News n "
                + "JOIN NewsCategory nc ON n.newsCategoryID = nc.newsCategoryID "
                + "JOIN Staff s ON n.staffID = s.staffID "
                + "ORDER BY n.postDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {

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
                String newsDescription = rs.getString("newsDescription");
                String newsCategoryName = rs.getString("newsCategoryName");
                String staffName = rs.getString("staffName");

                News news = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg, newsCategoryName, staffName, newsDescription);
                list.add(news);
            }

            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} news records for page {1}.", new Object[]{list.size(), page});

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching paginated news records.", e);
        }

        return list;
    }

    public List<News> getNewsByPageAndTitleAndCategory(String search, String categoryID, int page, int recordsPerPage) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT n.*, nc.name as newsCategoryName, s.name as staffName "
                + "FROM News n "
                + "JOIN NewsCategory nc ON n.newsCategoryID = nc.newsCategoryID "
                + "JOIN Staff s ON n.staffID = s.staffID "
                + "WHERE n.newsTitle LIKE ? AND nc.newsCategoryID = ? "
                + "ORDER BY n.postDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return list;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setString(1, "%" + search + "%");
            pre.setString(2, categoryID); // Filter by category
            pre.setInt(3, (page - 1) * recordsPerPage);
            pre.setInt(4, recordsPerPage);

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
                String newsDescription = rs.getString("newsDescription");
                String newsCategoryName = rs.getString("newsCategoryName");
                String staffName = rs.getString("staffName");

                News news = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg, newsCategoryName, staffName, newsDescription);
                list.add(news);
            }

            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} news records for page {1}.", new Object[]{list.size(), page});

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching paginated news records by title and category.", e);
        }

        return list;
    }

    public int getNumberOfRowsByTitleAndCategory(String title, String categoryID) {
        String sql = "SELECT COUNT(*) FROM News n JOIN NewsCategory nc ON n.newsCategoryID = nc.newsCategoryID "
                + "WHERE n.newsTitle LIKE ? AND nc.newsCategoryID = ?";
        int count = 0;

        try {
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return count;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setString(1, "%" + title + "%");
            pre.setString(2, categoryID);

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Total number of news records matching title '{0}' and category '{1}': {2}",
                    new Object[]{title, categoryID, count});

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error getting the number of news records by title and category.", e);
        }

        return count;
    }

    public int getNumberOfRowsByCategory(String categoryID) {
        String sql = "SELECT COUNT(*) FROM News WHERE newsCategoryID = ?";
        int count = 0;

        try {
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return count;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setString(1, categoryID); // Filter by categoryID

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Total number of news records for category '{0}': {1}", new Object[]{categoryID, count});

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error getting the number of news records by category.", e);
        }

        return count;
    }

    public List<News> getNewsByPageAndCategory(String categoryID, int page, int recordsPerPage) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT n.*, nc.name as newsCategoryName, s.name as staffName "
                + "FROM News n "
                + "JOIN NewsCategory nc ON n.newsCategoryID = nc.newsCategoryID "
                + "JOIN Staff s ON n.staffID = s.staffID "
                + "WHERE nc.newsCategoryID = ? "
                + "ORDER BY n.postDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return list;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setString(1, categoryID); // Filter by categoryID
            pre.setInt(2, (page - 1) * recordsPerPage); // Calculate offset
            pre.setInt(3, recordsPerPage); // Set limit

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
                String newsDescription = rs.getString("newsDescription");
                String newsCategoryName = rs.getString("newsCategoryName");
                String staffName = rs.getString("staffName");

                News news = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg, newsCategoryName, staffName, newsDescription);
                list.add(news);
            }

            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} news records for page {1} in category '{2}'.",
                    new Object[]{list.size(), page, categoryID});

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching paginated news records by category.", e);
        }

        return list;
    }

    //get by title with pagination
    public List<News> getNewsByPageAndTitle(String search, int page, int recordsPerPage) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT n.*, nc.name as newsCategoryName, s.name as staffName "
                + "FROM News n "
                + "JOIN NewsCategory nc ON n.newsCategoryID = nc.newsCategoryID "
                + "JOIN Staff s ON n.staffID = s.staffID "
                + "WHERE n.newsTitle LIKE ? "
                + "ORDER BY n.postDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {

            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return list;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            String searchTitle = "%" + search + "%";
            pre.setString(1, searchTitle);
            pre.setInt(2, (page - 1) * recordsPerPage); // Calculate offset
            pre.setInt(3, recordsPerPage);              // Set the limit
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
                String newsDescription = rs.getString("newsDescription");
                String newsCategoryName = rs.getString("newsCategoryName");
                String staffName = rs.getString("staffName");

                News news = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg, newsCategoryName, staffName, newsDescription);
                list.add(news);
            }

            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} news records for page {1}.", new Object[]{list.size(), page});

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching paginated news records.", e);
        }

        return list;
    }

    //get news for banner in /News
    public List<News> getNewsForBanner() {
        List<News> list = new ArrayList<>();
        String sql = "SELECT n.*, nc.name as newsCategoryName, s.name as staffName "
                + "FROM News n "
                + "JOIN NewsCategory nc ON n.newsCategoryID = nc.newsCategoryID "
                + "JOIN Staff s ON n.staffID = s.staffID "
                + "WHERE n.newsCategoryID = 1 "
                + "ORDER BY n.postDate DESC "
                + "OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY";

        try {

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
                String newsCategoryName = rs.getString("newsCategoryName");
                String staffName = rs.getString("staffName");

                News news = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg, newsCategoryName, staffName);
                list.add(news);
            }

            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} news records for the banner.", list.size());

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching news for the banner.", e);
        }

        return list;
    }

    public int getNumberOfRowsForTin() {
        String sql = "SELECT COUNT(*) FROM News Where newsCategoryID= 1";
        int count = 0;

        try {

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
    
    public int getNumberOfRows() {
        String sql = "SELECT COUNT(*) FROM News";
        int count = 0;

        try {

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

    public int getNumberOfRowsByTitle(String title) {
        String sql = "SELECT COUNT(*) FROM News WHERE newsTitle LIKE ?";
        int count = 0;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return count;
            }

            // Prepare the SQL statement
            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setString(1, "%" + title + "%"); // Use wildcard search for the title

            // Execute the query
            ResultSet rs = pre.executeQuery();

            // Get the count of matching rows
            if (rs.next()) {
                count = rs.getInt(1);
            }

            // Close resources
            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Total number of news records matching title '{0}': {1}", new Object[]{title, count});

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error getting the number of news records by title.", e);
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

    public News getNewsById2(int newsID) {
        News news = null;
        String sql = "SELECT n.*, nc.name as newsCategoryName "
                + "FROM News n "
                + "JOIN NewsCategory nc ON n.newsCategoryID = nc.newsCategoryID "
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
                int staffID = rs.getInt("staffID");  // Still retrieve staffID if needed
                int taskID = rs.getInt("taskID");    // Still retrieve taskID if needed
                int newsCategoryID = rs.getInt("newsCategoryID");
                String newsTitle = rs.getString("newsTitle");
                String newsContent = rs.getString("newsContent");
                java.sql.Timestamp sqlPostDate = rs.getTimestamp("postDate");
                Date postDate = new Date(sqlPostDate.getTime());
                String newsImg = rs.getString("newsImg");
                String newsDescription = rs.getString("newsDescription");

                String newsCategoryName = rs.getString("newsCategoryName");
                // Removed staffName since it's not fetched anymore

                news = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg, newsCategoryName, null, newsDescription); // Use null for staffName
            }

            rs.close();
            pre.close();
            LOGGER.log(Level.INFO, "Successfully retrieved news with ID {0}.", newsID);

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching news by ID.", e);
        }

        return news;
    }

    public boolean addNews(News news) {
        String sql = "INSERT INTO News (staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg, newsDescription) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        boolean isAdded = false;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return isAdded;
            }

            // Prepare the SQL statement with generated keys
            PreparedStatement pre = DBContext.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pre.setInt(1, news.getStaffID());
            pre.setInt(2, news.getTaskID());
            pre.setInt(3, news.getNewsCategoryID());
            pre.setString(4, news.getNewsTitle());

            //formatting newsContent, replace new line -> <br> #2
            String updatedNewsContent = news.getNewsContent().replace("\n", "<br>");
            pre.setString(5, updatedNewsContent);

            pre.setTimestamp(6, new java.sql.Timestamp(news.getPostDate().getTime()));  // Assuming news.getPostDate() returns a java.util.Date object
            pre.setString(7, news.getNewsImg());
            pre.setString(8, news.getDescription());

            // Execute the update
            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        news.setNewsID(generatedKeys.getInt(1)); // Set the auto-generated News ID
                        LOGGER.log(Level.INFO, "Added News with ID: {0}", news.getNewsID());
                        isAdded = true;
                    } else {
                        throw new SQLException("Adding news failed, no ID obtained.");
                    }
                }
            } else {
                LOGGER.log(Level.WARNING, "No news was added.");
            }

            // Close resources
            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error adding news.", e);
        }

        return isAdded;
    }

    public boolean deleteByNewsID(int newsID) {
        String sql = "DELETE FROM News WHERE newsID = ?";
        boolean isDeleted = false;

        try {
            // Get a connection from DBContext
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return isDeleted;
            }

            // Create a prepared statement
            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setInt(1, newsID);

            // Execute the deletion
            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                isDeleted = true;
                LOGGER.log(Level.INFO, "Successfully deleted News with ID: {0}", newsID);
            } else {
                LOGGER.log(Level.WARNING, "No News found with ID: {0}", newsID);
            }

            // Close the prepared statement
            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error deleting News with ID: {0}", newsID);
            e.printStackTrace();
        }

        return isDeleted;
    }

    public boolean updateNews(News news) {
        String sql = "UPDATE News SET staffID = ?, taskID = ?, newsCategoryID = ?, newsTitle = ?, newsContent = ?, postDate = ?, newsImg = ? WHERE newsID = ?";
        boolean updated = false;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return false;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setInt(1, news.getStaffID());
            pre.setInt(2, news.getTaskID());
            pre.setInt(3, news.getNewsCategoryID());
            pre.setString(4, news.getNewsTitle());
            pre.setString(5, news.getNewsContent());
            pre.setTimestamp(6, new java.sql.Timestamp(news.getPostDate().getTime()));
            pre.setString(7, news.getNewsImg());
            pre.setInt(8, news.getNewsID());

            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                updated = true;
                LOGGER.log(Level.INFO, "Successfully updated news with ID: {0}", news.getNewsID());
            } else {
                LOGGER.log(Level.WARNING, "No news record found with ID: {0}", news.getNewsID());
            }

            // Close resources
            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error updating news record.", e);
        }

        return updated;
    }

    public List<News> getTeamMembers() {
        List<News> teamMembers = new ArrayList<>();
        String sql = "SELECT n.*, nc.name as newsCategoryName, s.name as staffName "
                + "FROM News n "
                + "JOIN NewsCategory nc ON n.newsCategoryID = nc.newsCategoryID "
                + "JOIN Staff s ON n.staffID = s.staffID "
                + "WHERE nc.name = 'Thành viên'";

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return teamMembers;
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
                String newsCategoryName = rs.getString("newsCategoryName");
                String staffName = rs.getString("staffName");
                String description = rs.getString("newsDescription");

                // Create a News object for each team member
                News member = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg, newsCategoryName, staffName, description);

                // Add each member to the list
                teamMembers.add(member);
            }

            rs.close();
            pre.close();
            LOGGER.log(Level.INFO, "Successfully retrieved {0} team members.", teamMembers.size());

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching team members.", e);
        }

        return teamMembers;
    }
    
    public List<News> getBannerHomepage() {
        List<News> teamMembers = new ArrayList<>();
        String sql = "SELECT n.*, nc.name as newsCategoryName, s.name as staffName "
                + "FROM News n "
                + "JOIN NewsCategory nc ON n.newsCategoryID = nc.newsCategoryID "
                + "JOIN Staff s ON n.staffID = s.staffID "
                + "WHERE nc.name = 'banner home'";

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return teamMembers;
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
                String newsCategoryName = rs.getString("newsCategoryName");
                String staffName = rs.getString("staffName");
                String description = rs.getString("newsDescription");

                // Create a News object for each team member
                News member = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg, newsCategoryName, staffName, description);

                // Add each member to the list
                teamMembers.add(member);
            }

            rs.close();
            pre.close();
            LOGGER.log(Level.INFO, "Successfully retrieved {0} team members.", teamMembers.size());

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching team members.", e);
        }

        return teamMembers;
    }
    
    public List<News> getNewsByCategoryId(int newsCategoryID) {
    List<News> newsList = new ArrayList<>(); // Initialize an empty list to hold news articles
    String sql = "SELECT n.*, nc.name AS newsCategoryName, s.name AS staffName "
                + "FROM News n "
                + "JOIN NewsCategory nc ON n.newsCategoryID = nc.newsCategoryID "
                + "JOIN Staff s ON n.staffID = s.staffID "
                + "WHERE n.newsCategoryID = ?";

    try {
        // Initialize the connection
        DBContext.getConnection();

        if (DBContext.connection == null || DBContext.connection.isClosed()) {
            LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
            return null;
        }

        PreparedStatement pre = DBContext.connection.prepareStatement(sql);
        pre.setInt(1, newsCategoryID); 
        ResultSet rs = pre.executeQuery();

        while (rs.next()) { // Iterate through the ResultSet
            int newsID = rs.getInt("newsID");
            int staffID = rs.getInt("staffID");
            int taskID = rs.getInt("taskID");
            
            String newsTitle = rs.getString("newsTitle");
            String newsContent = rs.getString("newsContent");
            java.sql.Timestamp sqlPostDate = rs.getTimestamp("postDate");
            Date postDate = new Date(sqlPostDate.getTime());
            String newsImg = rs.getString("newsImg");
            String newsCategoryName = rs.getString("newsCategoryName");
            String staffName = rs.getString("staffName");
            String description = rs.getString("newsDescription");

            News news = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate, newsImg, newsCategoryName, staffName,description);
            newsList.add(news); // Add each news article to the list
        }

        rs.close();
        pre.close();
        LOGGER.log(Level.INFO, "Successfully retrieved news for category ID {0}.", newsCategoryID);

    } catch (SQLException | ClassNotFoundException e) {
        LOGGER.log(Level.SEVERE, "Error fetching news by category ID.", e);
    }

    return newsList; // Return the list of news articles
}

    public static void main(String[] args) {
        NewsDAO dao = new NewsDAO();
        News newNews = new News();

        //test search by title
        String searchTerm = "maintenance";
        int page = 1;
        int recordsPerPage = 5;
        List<News> newsList = dao.getNewsByPageAndTitle(searchTerm, page, recordsPerPage);

        if (newsList.isEmpty()) {
            System.out.println("No news records found for the search term: " + searchTerm);
        } else {
            System.out.println("Retrieved news records for page " + page + " with search term '" + searchTerm + "':");
            for (News news : newsList) {
                System.out.println("News ID: " + news.getNewsID()
                        + ", Title: " + news.getNewsTitle()
                        + ", Posted Date: " + news.getPostDate());
            }
        }

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
//        List<News> newsList = dao.getAll();
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
//        int totalRows = dao.getNumberOfRows();
//        System.out.println("Total number of news records: " + totalRows);
//
//        // Determine number of pages
//        int recordsPerPage = 5;
//        int totalPages = (int) Math.ceil((double) totalRows / recordsPerPage);
//        System.out.println("Total number of pages: " + totalPages);
//
//        // Test getNewsByPage for page 1
//        int pageToTest = 1; // You can change this to test different pages
//        System.out.println("Fetching news for page " + pageToTest);
//        List<News> paginatedNews = dao.getNewsByPage(pageToTest, recordsPerPage);
//
//        if (!paginatedNews.isEmpty()) {
//            for (News news : paginatedNews) {
//                System.out.println("ID: " + news.getNewsID()
//                        + ", Title: " + news.getNewsTitle()
//                        + ", Content: " + news.getNewsContent()
//                        + ", Post Date: " + news.getPostDate()
//                        + ", Image URL: " + news.getNewsImg());
//            }
//        } else {
//            System.out.println("No news records found for page " + pageToTest);
//        }
        //test delete
//        int testNewsID = 31;  // Replace with a valid newsID that exists in your database for testing
//
//        boolean isDeleted = dao.deleteByNewsID(testNewsID);
//
//        if (isDeleted) {
//            System.out.println("Test passed! News with ID " + testNewsID + " was deleted successfully.");
//        } else {
//            System.out.println("Test failed! News with ID " + testNewsID + " was not found or couldn't be deleted.");
//        }
    }
}
