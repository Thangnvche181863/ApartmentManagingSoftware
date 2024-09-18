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

                News news = new News(newsID, staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate);
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

    //add 
    public boolean addNews(News news) {
        String sql = "INSERT INTO News (staffID, taskID, newsCategoryID, newsTitle, newsContent, postDate) VALUES (?, ?, ?, ?, ?, ?)";
        boolean isAdded = false;

        try {
            // Initialize the connection
            DBContext.getConnection();

            if (DBContext.connection == null || DBContext.connection.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return isAdded;
            }

            PreparedStatement pre = DBContext.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pre.setInt(1, news.getStaffID());
            pre.setInt(2, news.getTaskID());
            pre.setInt(3, news.getNewsCategoryID());
            pre.setString(4, news.getNewsTitle());
            pre.setString(5, news.getNewsContent());
            pre.setTimestamp(6, new java.sql.Timestamp(news.getPostDate().getTime()));

            int affectedRows = pre.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding news failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    news.setNewsID(generatedKeys.getInt(1));
                    LOGGER.log(Level.INFO, "Added News with ID: {0}", news.getNewsID());
                    isAdded = true;
                } else {
                    throw new SQLException("Adding news failed, no ID obtained.");
                }
            }

            pre.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error adding news.", e);
        }

        return isAdded;
    }

    public static void main(String[] args) {
        NewsDAO dao = new NewsDAO();
        News newNews = new News();
        newNews.setStaffID(1); // Assuming staffID 1 exists
        newNews.setTaskID(1); // Assuming taskID 1 exists
        newNews.setNewsCategoryID(1); // Assuming newsCategoryID 1 exists
        newNews.setNewsTitle("New Feature Released");
        newNews.setNewsContent("We have released a new feature that allows users to...");
        newNews.setPostDate(new java.util.Date());

        boolean isAdded = dao.addNews(newNews);
    if (isAdded) {
        System.out.println("Added News with ID: " + newNews.getNewsID());
    } else {
        System.out.println("Failed to add News.");
    }
        //test get all
//        List<News> newsList = dao.getAll();
//        if (!newsList.isEmpty()) {
//            for (News news : newsList) {
//                System.out.println("ID: " + news.getNewsID()
//                        + ", Title: " + news.getNewsTitle()
//                        + ", Content: " + news.getNewsContent()
//                        + ", Post Date: " + news.getPostDate());
//            }
//        } else {
//            System.out.println("No news records found.");
//        }
    }
}
