/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.DBContext;
import model.NewsCategory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.News;

/**
 *
 * @author PC
 */
public class NewsCategoryDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(NewsCategoryDAO.class.getName());
//get All

    public List<NewsCategory> getAll() {
        List<NewsCategory> list = new ArrayList<>();
        String sql = "SELECT * FROM NewsCategory";

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
                int newsCategoryId = rs.getInt("newsCategoryID");
                String newsCategoryName = rs.getString("name");
                String newsCategoryDescription = rs.getString("description");

                list.add(new NewsCategory(newsCategoryId, newsCategoryName, newsCategoryDescription));
            }

            // Close resources
            rs.close();
            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching news categories.", e);
        }

        return list;
    }
    
    public List<NewsCategory> getNewscategoryGreaterThan10() {
        List<NewsCategory> list = new ArrayList<>();
        String sql = "SELECT * FROM NewsCategory WHERE newsCategoryID > 10";

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
                int newsCategoryId = rs.getInt("newsCategoryID");
                String newsCategoryName = rs.getString("name");
                String newsCategoryDescription = rs.getString("description");

                list.add(new NewsCategory(newsCategoryId, newsCategoryName, newsCategoryDescription));
            }

            // Close resources
            rs.close();
            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching news categories.", e);
        }

        return list;
    }
    
    

    //add NewsCategory
    public boolean addNewsCategory(NewsCategory category) {
        String sql = "INSERT INTO NewsCategory (name, description) VALUES (?, ?)";
        boolean isAdded = false;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return isAdded;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pre.setString(1, category.getName());
            pre.setString(2, category.getDescription());

            int affectedRows = pre.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding news category failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    category.setNewsCategoryID(generatedKeys.getInt(1));
                    LOGGER.log(Level.INFO, "Added NewsCategory with ID: {0}", category.getNewsCategoryID());
                    isAdded = true;
                } else {
                    throw new SQLException("Adding news category failed, no ID obtained.");
                }
            }

            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error adding news category.", e);
        }

        return isAdded;
    }

    //delete by ID
    public boolean deleteNewsCategory(int newsCategoryId) {
        String sql = "DELETE FROM NewsCategory WHERE newsCategoryID = ?";
        boolean isDeleted = false;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return isDeleted;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setInt(1, newsCategoryId);

            int affectedRows = pre.executeUpdate();
            if (affectedRows > 0) {
                LOGGER.log(Level.INFO, "Deleted NewsCategory with ID: {0}", newsCategoryId);
                isDeleted = true;
            } else {
                LOGGER.log(Level.WARNING, "No NewsCategory found to delete with ID: {0}", newsCategoryId);
            }

            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error deleting news category.", e);
        }

        return isDeleted;
    }
    //Update by ID

    public boolean updateNewsCategory(NewsCategory category) {
        String sql = "UPDATE NewsCategory SET name = ?, description = ? WHERE newsCategoryID = ?";
        boolean isUpdated = false;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return isUpdated;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql);
            pre.setString(1, category.getName());
            pre.setString(2, category.getDescription());
            pre.setInt(3, category.getNewsCategoryID());

            int affectedRows = pre.executeUpdate();
            if (affectedRows > 0) {
                LOGGER.log(Level.INFO, "Updated NewsCategory with ID: {0}", category.getNewsCategoryID());
                isUpdated = true;
            } else {
                LOGGER.log(Level.WARNING, "No NewsCategory found with ID: {0}", category.getNewsCategoryID());
            }

            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error updating news category.", e);
        }

        return isUpdated;
    }

    public int getNumberOfRows() {
        String sql = "SELECT COUNT(*) FROM NewsCategory";
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

    public List<NewsCategory> getNewsCategoryByPage(int page, int recordsPerPage) {
        List<NewsCategory> list = new ArrayList<>();
        String sql = "SELECT * FROM NewsCategory "
                + "ORDER BY newsCategoryID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

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
                int newsCategoryID = rs.getInt("newsCategoryID");
                String newsCategoryName = rs.getString("name");
                String newsCategoryDescription = rs.getString("description");

                NewsCategory nc = new NewsCategory(newsCategoryID, newsCategoryName, newsCategoryDescription);

                list.add(nc);
            }

            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} newscategory records for page {1}.", new Object[]{list.size(), page});

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching paginated newscategory records.", e);
        }

        return list;
    }

    public int getNumberOfRowsByName(String name) {
        String sql = "SELECT COUNT(*) FROM NewsCategory WHERE name LIKE ?";
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
            pre.setString(1, "%" + name + "%"); // Use wildcard search for the title

            // Execute the query
            ResultSet rs = pre.executeQuery();

            // Get the count of matching rows
            if (rs.next()) {
                count = rs.getInt(1);
            }

            // Close resources
            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Total number of news records matching title '{0}': {1}", new Object[]{name, count});

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error getting the number of news records by title.", e);
        }

        return count;
    }

    public List<NewsCategory> getNewsCategoryByPageAndName(String search, int page, int recordsPerPage) {
        List<NewsCategory> list = new ArrayList<>();
        String sql = "SELECT * FROM NewsCategory "
                + "WHERE name LIKE ? "
                + "ORDER BY newsCategoryID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

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

                int newsCategoryID = rs.getInt("newsCategoryID");
                String newsCategoryName = rs.getString("name");
                String newsCategoryDescription = rs.getString("description");

                NewsCategory nc = new NewsCategory(newsCategoryID, newsCategoryName, newsCategoryDescription);

                list.add(nc);
            }

            rs.close();
            pre.close();

            LOGGER.log(Level.INFO, "Successfully retrieved {0} news records for page {1}.", new Object[]{list.size(), page});

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error fetching paginated news records.", e);
        }

        return list;
    }

    public static void main(String[] args) {
        //test DAO code
        // Initialize the DAO
        NewsCategoryDAO ncDAO = new NewsCategoryDAO();

        // Set up parameters for the test
        String searchTerm = "t"; // The search term we're testing
        int currentPage = 1; // Test for the first page
        int recordsPerPage = 5; // Limit of records per page

        // Call the method to test
        List<NewsCategory> result = ncDAO.getNewsCategoryByPageAndName(searchTerm, currentPage, recordsPerPage);

        // Output the results
        System.out.println("Search results for term '" + searchTerm + "':");
        for (NewsCategory nc : result) {
            System.out.println("ID: " + nc.getNewsCategoryID() + ", Name: " + nc.getName() + ", Description: " + nc.getDescription());
        }
    }

}
