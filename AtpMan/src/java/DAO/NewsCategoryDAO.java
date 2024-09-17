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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static void main(String[] args) {
        //test DAO code
        NewsCategoryDAO dao = new NewsCategoryDAO();
        NewsCategory newCategory = new NewsCategory();
        //test get All
        List<NewsCategory> list = dao.getAll();
        if (!list.isEmpty()) {
        for (NewsCategory category : list) {
            System.out.println("ID: " + category.getNewsCategoryID() +
                               ", Name: " + category.getName() +
                               ", Description: " + category.getDescription());
        }
    } else {
        System.out.println("No news categories found.");
    }
        
        
        
        
//        System.out.println(list.get(0).getName());
        //test add
//        newCategory.setName("Test");
//        newCategory.setDescription("Test.");
//        boolean isAdded = dao.addNewsCategory(newCategory);
//        if (isAdded) {
//            System.out.println("Added NewsCategory with ID: " + newCategory.getNewsCategoryID());
//        } else {
//            System.out.println("Failed to add NewsCategory.");
//        }


        // Test Delete the NewsCategory by ID
//        newCategory.setNewsCategoryID(12);
//    boolean isDeleted = dao.deleteNewsCategory(newCategory.getNewsCategoryID());
//    if (isDeleted) {
//        System.out.println("Deleted NewsCategory with ID: " + newCategory.getNewsCategoryID());
//    } else {
//        System.out.println("Failed to delete NewsCategory.");
//    }


        //Test update by ID
//        newCategory.setDescription("Updated description for Test.");
//        newCategory.setName("Updated name for Test.");
//        newCategory.setNewsCategoryID(12);
//        boolean isUpdated = dao.updateNewsCategory(newCategory);
//        if (isUpdated) {
//            System.out.println("Updated NewsCategory with ID: " + newCategory.getNewsCategoryID());
//        } else {
//            System.out.println("Failed to update NewsCategory.");
//        }
    }
}
